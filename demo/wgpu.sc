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
    let name = TS.name
    dispatch TS.storage
    case Pointer (mutable? T)
        let prefix = (? mutable? "mutable" "")
        f"(${prefix}@ ${T})"
    case FunctionPointer (retT params)
        let params =
            fold (result = "") for p in params
                result .. (tostring p) .. " "
        f"(@ (function ${retT} ${params}))"
    case Tuple (fields)
        let fields =
            fold (result = "") for f in fields
                .. result f"(${f.field-name} = ('storageof ${f.T}))"
        f"(tuple.type ${fields})"
    default
        "(storageof Nothing)"

for st in bindings.storages
    try
        # if it doesn't exist in the map, then it's an alias
        let tname = ('get bindings.typename-sym-lookup st.name)
        print
            f"'set-plain-storage ${st.name} ${gen-type-definition st bindings}"
    else
        print f"let ${st.name} = ${gen-type-definition st bindings}"
       


print "none"



# for st in bindings.storages
#     print st.name st.storage
#         dispatch st.storage
#         case TypeReference (T)
#             tostring T
#         case Pointer (mutable? T)
#             let mutable-mod = (? mutable? "mutable " "")
#             f"(${mutable-mod}pointer ${tostring T})"
#         default
#             ""

# for f in bindings.functions
#     print f.name f.storage
#     using bindgen
#     assert (('literal f.storage) == StorageKind.FunctionPointer.Literal)
#     dispatch f.storage
#     case FunctionPointer (retT params)
#         io-write! (.. (tostring retT) " <- (")
#         for i p in (enumerate params)
#             if (i < ((countof params) - 1))
#                 io-write! (.. (tostring p) " ")
#             else
#                 io-write! (tostring p)
#         io-write! ")\n"

#     default
#         ;

none
