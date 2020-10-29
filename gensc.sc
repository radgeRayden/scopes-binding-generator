using import radlib.stringtools
import .cjson

inline json-array->generator (arr)
    Generator
        inline "start" ()
            arr.child
        inline "valid?" (self)
            self != null
        inline "at" (self)
            self
        inline "next" (self)
            self.next

fn gen-pointer-type (T mutable?)
    let flag = (? (mutable? as bool) (bxor pointer-flag-non-writable -1:u64) pointer-flag-non-writable)
    f"(sc_pointer_type ${T} ${flag}:u64 unnamed)"

fn emit-type-definition (storage bindings)
    let name = (string (cjson.GetStringValue (cjson.GetObjectItem storage "typename")))
    let kind = (string (cjson.GetStringValue (cjson.GetObjectItem storage "kind")))

    inline wrap (def)
        let typenames = (cjson.GetObjectItem bindings "typenames")
        let alias? = (not (cjson.HasObjectItem typenames name))
        if alias?
            f"let ${name} = ${def}"
        else
            f"sc_typename_type_set_storage ${name} ${def} typename-flag-plain"

    vvv print
    match kind
    case "pointer"
        let mutable? = (cjson.IsTrue (cjson.GetObjectItem storage "mutable?"))
        let T = (string (cjson.GetStringValue (cjson.GetObjectItem storage "type")))
        wrap (gen-pointer-type T mutable?)
    case "function-pointer"
        let retT =
            string (cjson.GetStringValue (cjson.GetObjectItem storage "return-type"))
        let params = (cjson.GetObjectItem storage "parameters")
        let count = (cjson.GetArraySize params)
        let params* =
            fold (result = "") for i p in (enumerate (json-array->generator params))
                let p = (string (cjson.GetStringValue p))
                result ..
                    interpolate "store ${p} (getelementptr type-buffer ${i})\n"
        let fndef = f"(sc_function_type ${retT} ${count} type-buffer)"
        params* .. (wrap f"${gen-pointer-type fndef false}")
    case "struct"
        let fields =
            (cjson.GetObjectItem storage "fields")
        let count = (cjson.GetArraySize fields)
        let fields* =
            fold (result = "") for i f in (enumerate (json-array->generator fields))
                let field-name =
                    string (cjson.GetStringValue (cjson.GetObjectItem f "name"))
                let fT =
                    string (cjson.GetStringValue (cjson.GetObjectItem f "type"))
                .. result
                    f"store (sc_key_type '${field-name} ${fT}) (getelementptr type-buffer ${i})"
                    "\n"
        fields* .. (wrap f"(sc_tuple_type ${count} type-buffer)")
    case "enum"
        let fields =
            (cjson.GetObjectItem storage "fields")
        let head = (.. (wrap i32) "\n")
        fold (result = head) for f in (json-array->generator fields)
            let field-name =
                string (cjson.GetStringValue (cjson.GetObjectItem f "name"))
            let constant =
                string (cjson.GetStringValue (cjson.GetObjectItem f "constant"))
            let field =
                interpolate "sc_type_set_symbol ${name} '${field-name} ${constant}\n"
            .. result field
    case "union"
        error "NYI"
    case "alias"
        let ref =
            string (cjson.GetStringValue (cjson.GetObjectItem storage "type"))
        wrap ref
    case "opaque"
        f"sc_typename_type_set_opaque ${name}"
    default
        ""

fn emit-typename (tname)
    """"Creates a new typename, effectively forward declaring the type.
    let name super =
        string
            cjson.GetStringValue
                cjson.GetObjectItem tname "name"
        string
            cjson.GetStringValue
                cjson.GetObjectItem tname "super"

    io-write! f""""let ${name} = (sc_typename_type "${name}" ${super})

fn from-JSON (jsondata)
    # FIXME: should first go through the exports to verify the maximum tuple/function size
    print "let type-buffer = (alloca-array type 128)"

    let typenames = (cjson.GetObjectItem jsondata "typenames")
    assert (cjson.IsArray typenames)
    let storages = (cjson.GetObjectItem jsondata "storages")
    assert (cjson.IsArray storages)
    for tname in (json-array->generator typenames)
        emit-typename tname
    for storage in (json-array->generator storages)
        emit-type-definition storage jsondata

fn from-include-scope (scope)
    import .generator
    from-JSON
        generator.gen-bindings-JSON scope

do
    let from-JSON from-include-scope
    locals;
