using import radlib.stringtools
let bindgen = (import ..typeinfo)

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

    let extern-filter = "^wgpu_"
    vvv bind extern
    do
        using header.extern filter extern-filter
        locals;
    unlet header
    locals;

let bindings =
    bindgen.gen-header-type-info wgpu-header

fn emit-typename (tname)
    """"Creates a new typename, effectively forward declaring the type.
    io-write! f""""let ${tname.name} = (sc_typename_type "${tname.name}" ${tname.super})

fn gen-pointer-type (T mutable?)
    let flag = (? mutable? (bxor pointer-flag-non-writable -1:u64) pointer-flag-non-writable)
    f"(sc_pointer_type ${T} ${flag}:u64 unnamed)"

# FIXME: should first go through the exports to verify the maximum tuple/function size
print "let type-buffer = (alloca-array type 128)"

for tname in bindings.typenames
    emit-typename tname

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
        wrap (gen-pointer-type T mutable?)
    case FunctionPointer (retT params)
        let count = (countof params)
        let params =
            fold (result = "") for i p in (enumerate params)
                interpolate "store ${p} (getelementptr type-buffer ${i})\n"
        let fndef = f"(sc_function_type ${retT} ${count} type-buffer)"
        params .. (wrap f"${gen-pointer-type fndef false}")
    case Tuple (fields)
        let count = (countof fields)
        let fields =
            fold (result = "") for i f in (enumerate fields)
                .. result
                    f"store (sc_key_type '${f.field-name} ${f.T}) (getelementptr type-buffer ${i})"
                    "\n"
        fields .. (wrap f"(sc_tuple_type ${count} type-buffer)")
    case Enum (fields)
        let head = (.. (wrap i32) "\n")
        fold (result = head) for f in fields
            let field =
                interpolate "sc_type_set_symbol ${TS.name} '${f.field-name} ${f.constant}\n"
            .. result field
    case Union (fields)
        error "not yet implemented"
    case TypeReference (ref)
        wrap ref
    case Opaque ()
        f"sc_typename_type_set_opaque ${TS.name}"
    default
        unreachable;

for st in bindings.storages
    print
        f"${gen-type-definition st bindings}"

for st in bindings.functions
    let SK = bindgen.StorageKind
    if (('literal st.storage) == SK.FunctionPointer.Literal)
        let retT params = ('unsafe-extract-payload st.storage SK.FunctionPointer.Type)
        let count = (countof params)
        let params =
            fold (result = "") for i p in (enumerate params)
                result .. (interpolate "store ${p} (getelementptr type-buffer ${i})\n")
        let fndef =
            f"(sc_function_type ${retT} ${count} type-buffer)"
        let flags = (global-flag-non-writable | global-flag-non-readable)
        print (params .. f"let ${st.name} = (sc_global_new '${st.name} ${fndef} ${flags} unnamed)")
    else
        error "expected function pointer"

print "do"
print "    let"
for tname in bindings.typenames
    print f"        ${tname.name}"
print "    locals;"
