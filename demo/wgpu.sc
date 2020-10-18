using import radlib.stringtools
import ..cjson
let bindgen = (import ..typeinfo)

let bindings =
    bindgen.gen-header-type-info """"#include "wgpu.h"
        '()
        "^(WGPU|wgpu_)"

for st in bindings.storages
    print st.name st.storage
        dispatch st.storage
        case TypeReference (T)
            tostring T
        case Pointer (mutable? T)
            let mutable-mod = (? mutable? "mutable " "")
            f"(${mutable-mod}pointer ${tostring T})"
        default
            ""

for f in bindings.functions
    print f.name f.storage
    using bindgen
    assert (('literal f.storage) == StorageKind.FunctionPointer.Literal)
    dispatch f.storage
    case FunctionPointer (retT params)
        io-write! (.. (tostring retT) " <- (")
        for i p in (enumerate params)
            if (i < ((countof params) - 1))
                io-write! (.. (tostring p) " ")
            else
                io-write! (tostring p)
        io-write! ")\n"

    default
        ;

none
