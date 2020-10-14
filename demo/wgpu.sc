import ..cjson
let bindgen = (import ..rewrite)

let bindings =
    bindgen.gen-bindings-object """"#include "wgpu.h"
        '()
        "^WGPU"

for el in bindings.storages
    print el.name
        'apply el.storage
            (T self) -> (repr self)
none
