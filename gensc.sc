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
    let typenames = (cjson.GetObjectItem bindings "typenames")

    inline wrap (def)
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
        let head =
            interpolate "sc_typename_type_set_storage ${name} i32 typename-flag-plain\n"
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
        string tname.string
        string (cjson.GetStringValue tname)

    io-write! f""""let ${name} = (sc_typename_type "${name}" ${super})

fn emit-extern-fn (fdef)
    let name =
        string
            cjson.GetStringValue
                cjson.GetObjectItem fdef "name"
    let kind =
        string
            cjson.GetStringValue
                cjson.GetObjectItem fdef "kind"
    assert (kind == "function-pointer") "only function externs are supported at the moment"


    let retT =
        string
            cjson.GetStringValue
                cjson.GetObjectItem fdef "return-type"
    let params =
        cjson.GetObjectItem fdef "parameters"

    vvv io-write!
    fold (result = "") for i p in (enumerate (json-array->generator params))
        let p = (string (cjson.GetStringValue p))
        result ..
            interpolate "store ${p} (getelementptr type-buffer ${i})\n"

    let count = (cjson.GetArraySize params)
    let def =
        f"(sc_function_type ${retT} ${count} type-buffer)"
    let flags = (global-flag-non-writable | global-flag-non-readable)
    print f"let ${name} = (sc_global_new '${name} ${def} ${flags} unnamed)"

fn gen-constant-initializer (T args)
    match T
    case (or "i8" "u8" "i16" "u16" "i32" "u32" "i64" "u64")
        string
            cjson.GetStringValue
                cjson.GetObjectItem
                    cjson.GetArrayItem args 0
                    "value"
    # TODO: handle [ui]128
    case (or "f32" "f64")
        let v =
            cjson.GetObjectItem
                cjson.GetArrayItem args 0
                "value"
        assert (cjson.IsNumber v)
        tostring v.valuedouble
    default
        error "NYI"

fn from-JSON (jsondata)
    # FIXME: should first go through the exports to verify the maximum tuple/function size
    print "let type-buffer = (alloca-array type 128)"

    let typenames = (cjson.GetObjectItem jsondata "typenames")
    assert (cjson.IsObject typenames)
    let storages = (cjson.GetObjectItem jsondata "storages")
    assert (cjson.IsArray storages)
    let externs = (cjson.GetObjectItem jsondata "externs")
    assert (cjson.IsArray externs)
    let defines = (cjson.GetObjectItem jsondata "defines")
    assert (cjson.IsArray defines)

    for tname in (json-array->generator typenames)
        emit-typename tname
    for storage in (json-array->generator storages)
        emit-type-definition storage jsondata
    for f in (json-array->generator externs)
        emit-extern-fn f

    print "do"
    print "    let"
    for tname in (json-array->generator typenames)
        print f"        ${string tname.string}"
    for ext in (json-array->generator externs)
        let name =
            string
                cjson.GetStringValue
                    cjson.GetObjectItem ext "name"
        print f"        ${name}"

    for const in (json-array->generator defines)
        let name =
            string
                cjson.GetStringValue
                    cjson.GetObjectItem const "name"
        let T =
            string
                cjson.GetStringValue
                    cjson.GetObjectItem const "type"
        let args = (cjson.GetObjectItem const "args")
        print f"    let ${name} = ${gen-constant-initializer T args}"
    print "    locals;"

fn from-include-scope (scope)
    import .generator
    from-JSON
        generator.gen-bindings-JSON scope

do
    let from-JSON from-include-scope
    locals;
