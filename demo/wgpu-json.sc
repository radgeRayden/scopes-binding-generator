import ..cjson
import ..generator

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

using import radlib.libc
stdio.printf "%s\n" (cjson.Print (generator.gen-bindings-JSON wgpu-header))
