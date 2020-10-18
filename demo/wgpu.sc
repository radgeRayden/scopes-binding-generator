using import radlib.stringtools
import ..cjson
let bindgen = (import ..typeinfo)

let bindings =
    bindgen.gen-header-type-info """"#include "wgpu.h"
        '()
        "^(WGPU|wgpu_)"

fn emit-typename (tname)
    """"Creates a new typename, effectively forward declaring the type.
    io-write! f""""let ${tname.name} = (sc_typename_type "${tname.name}" ${tname.super})

for tname in bindings.typenames
    emit-typename tname

print "run-stage;"

fn gen-type-definition (TS bindings)
    inline wrap (storage)
        let name = TS.name
        let alias? = (not ('in? bindings.typename-sym-lookup name))
        if alias?
            f"let ${name} = ${storage}"
        else
            f"sc_typename_type_set_storage ${name} ${storage} typename-flag-plain"

    dispatch TS.storage
    case Pointer (mutable? T)
        let prefix = (? mutable? "mutable" "")
        wrap f"(${prefix}@ ${T})"
    case FunctionPointer (retT params)
        let params =
            fold (result = "") for p in params
                result .. (tostring p) .. " "
        wrap f"(@ (function ${retT} ${params}))"
    case Tuple (fields)
        let fields =
            fold (result = "") for f in fields
                .. result f"(${f.field-name} = ${f.T})"
        wrap f"(tuple.type ${fields})"
    case Enum (fields)
        let head = (.. (wrap i32) "\n")
        fold (result = head) for f in fields
            let field =
                interpolate "sc_type_set_symbol ${TS.name} '${f.field-name} ${f.constant}\n"
            .. result field
    case Union (fields)
        error "not yet implemented"
    case TypeReference (ref)
        f"let ${TS.name} = ${ref}"
    case Opaque ()
        f"sc_typename_type_set_opaque ${TS.name}"
    default
        unreachable;

for st in bindings.storages
    print
        f"${gen-type-definition st bindings}"

for st in bindings.functions
    dispatch st.storage
    case FunctionPointer (retT params)
        let params =
            fold (result = "") for p in params
                result .. (tostring p) .. " "
        let fndef =
            f"(function ${retT} ${params})"
        print
            f"let ${st.name} = (extern '${st.name} ${fndef})"
    default
        error "expected function pointer"

print "none"
