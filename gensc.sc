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
    for tname in (json-array->generator typenames)
        emit-typename tname

fn from-include-scope (scope)
    import .generator
    from-JSON
        generator.gen-bindings-JSON scope

do
    let from-JSON from-include-scope
    locals;
