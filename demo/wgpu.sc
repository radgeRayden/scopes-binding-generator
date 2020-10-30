using import radlib.stringtools
let bindgen = (import ..gensc)

# this can be automated by using the runtime Scope functions
vvv bind wgpu-header
do
    let header =
        include "wgpu.h"
    let type-filter = "^WGPU"
    vvv bind typedef
    do
        using header.typedef filter type-filter
        locals;
    vvv bind enum
    do
        using header.enum filter type-filter
        locals;
    vvv bind struct
    do
        using header.struct filter type-filter
        locals;
    vvv bind union
    do
        using header.union filter type-filter
        locals;

    vvv bind define
    do
        using header.define filter type-filter
        locals;

    let extern-filter = "^wgpu_"
    vvv bind extern
    do
        using header.extern filter extern-filter
        locals;
    unlet header
    locals;

vvv bind transformers
do
    fn symbol-transformer (sym)
        let match? start end = ('match? "^(wgpu_|WGPU)" sym)
        if match?
            rslice sym end
        else
            sym
    locals;

bindgen.from-include-scope wgpu-header transformers
