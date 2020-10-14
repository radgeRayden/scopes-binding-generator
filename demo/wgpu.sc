import ..cjson
let bindgen = (import ..rewrite)

let bindings =
    bindgen.gen-bindings-object """"#include "wgpu.h"
        '()
        "^WGPU"

for k v in bindings.typenames
    print k
