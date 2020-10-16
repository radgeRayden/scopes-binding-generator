using import struct
using import Map
using import Array
using import enum

import .cjson
using import radlib.stringtools
using import itertools

# WORKFLOW
# ================================================================================
    In order to generate bindings, we need:
    - a full list of typenames, so they can be forward declared and
    pointers can refer to them even in the case of circular dependencies.
    - a topologically sorted array of types with their storages, or
    an indication that they're opaque.
    - a list of function interfaces.

    It's important that the storages agree with the typenames, so we don't have duplicates
    or that it's impossible to find the type they're referencing. Also we need to account for
    anonymous structs / unions, and give them unique identifiers.

    We sort types by recursively introspecting them and defining types we haven't seen
    yet as we go. This means we need both an ordered storage definition array and a lookup map
    (so we can check whether the type has been defined or not). Again it is important
    that we properly define this map, as we need the type identifier to be unique.
    The best way to achieve this is by using the type itself as recognized by the compiler.
    Types are represented by opaque pointers. We can directly store the hash of the type,
    to make serialization easier.
# ================================================================================

enum StorageKind
    Pointer : (mutable? = bool) (T = Symbol)
    FunctionPointer : (retT = Symbol) (args = (Array Symbol))
    Tuple : (Array (tuple (field-name = Symbol) (T = Symbol)))
    Enum : (Array (tuple (field-name = Symbol) (constant = u64)))
    Union : (Array (tuple (variant = Symbol) (T = Symbol)))
    # can be used for opaque, or builtin types, or simply to avoid redefining types
    # where necessary.
    TypeReference : Symbol

struct TypeStorage
    name : Symbol
    storage : StorageKind

struct HeaderBindings
    typenames : (Map Symbol hash)
    typename-lookup : (Map hash Symbol)
    # topologically sorted array of storage types
    storages : (Array TypeStorage)
    # easy lookup, has to be set whenever storages is appended.
    storage-lookup : (Map Symbol TypeStorage)

    fn add-typename (self sym T)
        'set self.typenames sym (hash T)
        'set self.typename-lookup (hash T) sym
        ;

    fn add-storage (self sym T)
        raising Error
        try
            # have we defined this already?
            # we must lookup by symbol because a lot of typedefs are aliases to
            # builtin types.
            let defined =
                'get self.storage-lookup sym
            return
                TypeStorage defined.name
                    StorageKind.TypeReference defined.name
        else
            # go on, then
            ;
        # let super = ('superof T)
        # let stkind =
        #     match super
        #     case function
        #         # functions are never defined outside of pointers, so we can skip adding
        #         # it to our list. However we might still want to add its dependencies.
        #         local args : (Array StorageKind)
        #         'append args
        #             copy
        #                 (this-function (get-typename T bindings) ('return-type T) bindings) . storage
        #         for el in ('elements T)
        #             'append args
        #                 copy
        #                     (this-function (get-typename el bindings) el bindings) . storage
        #         return
        #             TypeStorage 'Unknown (StorageKind.Function (Rc.wrap (deref args)))
        #     case (or real integer)
        #         if (type-builtin? T)
        #             StorageKind.Native (Symbol (tostring ('storageof T)))
        #         else
        #             # honestly not sure what to do here. I suspect this branch never hits.
        #             error "unexpected typedef aliasing"
        #     case CStruct
        #         StorageKind.Opaque;
        #         # if ('opaque? T)
        #         #     StorageKind.Opaque;
        #         # else
        #         #     StorageKind.Composite;
        #     case CUnion
        #         StorageKind.Opaque;
        #         # if ('opaque? T)
        #         #     StorageKind.Opaque;
        #         # else
        #         #     StorageKind.Composite;
        #     case CEnum
        #         StorageKind.Opaque;
        #         # if ('opaque? T)
        #         #     StorageKind.Opaque;
        #         # else
        #         #     StorageKind.Composite;
        #     case pointer
        #         let innerT = ('element@ T 0)
        #         let newST =
        #             copy
        #                 (this-function (get-typename innerT bindings) innerT bindings) . storage
        #         StorageKind.Pointer (Rc.wrap newST)
        #     default
        #         StorageKind.Opaque;

        # let TS =
        #     TypeStorage
        #         name = sym
        #         storage = stkind

        # 'set bindings.defined-types sym (copy TS)
        # 'append bindings.storages (copy TS)
        # # if we got here, this is a type that can be referenced.
        TypeStorage sym (StorageKind.TypeReference sym)

fn import-bindings (includestr opt)
    sc_import_c "bindings.c" includestr opt (Scope)

fn gen-bindings-object (includestr opt filter)
    local bindings = (HeaderBindings)
    let header = (import-bindings includestr opt)

    # insert builtin types first as references
    va-map
        inline (T)
            'add-typename bindings (Symbol (tostring T)) T
        _ i8 u8 i16 u16 i32 u32 i64 u64 f32 f64

    # collect typenames
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let match? start end = ('match? filter (k as string))
                if match?
                    let T = (v as type)
                    'add-typename bindings k T
        _ 'typedef 'enum 'struct 'union
    # recursively define types
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let match? start end = ('match? filter (k as string))
                if match?
                    let T = (v as type)
                    'add-storage bindings (k as Symbol) T
        _ 'typedef 'enum 'struct 'union

    bindings

fn emit-bindings-JSON (includestr opt filter)
    let bindings = (cjson.CreateObject)
    let typename-array = (cjson.CreateArray)
    cjson.AddItemToObjectCS bindings "typenames" typename-array

    bindings

fn emit-bindings-stdout (includestr opt filter)

do
    let emit-bindings-JSON emit-bindings-stdout gen-bindings-object
    locals;
