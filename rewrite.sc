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
    Pointer : (pointer this-type)
    Composite : list
    Native : string
    Function : list
    TypeReference : string
    Opaque

    inline __copy (self)
        'apply self
            inline (T ...)
                T ...

    inline __drop (self)
        dispatch self
        case Pointer (ptr)
            # FIXME: if the pointer is nested, we leak
            free ptr
        default
            ;

struct TypeStorage
    name : Symbol
    storage : StorageKind
    inline __copy (self)
        this-type self.name (copy self.storage)

struct HeaderBindings
    typenames : (Map Symbol hash)
    typename-lookup : (Map hash Symbol)
    defined-types : (Map Symbol TypeStorage)
    # topologically sorted array of storage types
    storages : (Array TypeStorage)

fn type-builtin? (T)
    """"Test whether T is one of the default language types.
    va-lfold false
        inline (__ current computed)
            computed or (T == current)
        _ i8 u8 i16 u16 i32 u32 i64 u64 f16 f32 f64 void

fn get-typename (T bindings)
    if (type-builtin? T)
        tostring T
    else
        try
            tostring
                'get bindings.typename-lookup (hash T)
        else
            "Unknown"

fn walk-type (sym T bindings)
    try
        # have we defined this already?
        # we must lookup by symbol because a lot of typedefs are aliases to
        # builtin types.
        let defined =
            'get bindings.defined-types sym
        return
            TypeStorage defined.name
                StorageKind.TypeReference (tostring defined.name)
    else
        # go on, then
        ;
    if (type-builtin? T)
        return
            TypeStorage (Symbol (tostring T)) (StorageKind.Native (tostring T))

    let super = ('superof T)
    let stkind =
        match super
        case function
            # functions are never defined outside of pointers, so we can skip adding
            # it to our list. However we might still want to add its dependencies.
            StorageKind.Opaque;
        case (or real integer)
            if (type-builtin? T)
                StorageKind.Native (tostring ('storageof T))
            else
                # honestly not sure what to do here. I suspect this branch never hits.
                error "unexpected typedef aliasing"
        case CStruct
            if ('opaque? T)
                StorageKind.Opaque;
            else
                StorageKind.Composite (list)
        case CUnion
            if ('opaque? T)
                StorageKind.Opaque;
            else
                StorageKind.Composite (list)
        case CEnum
            if ('opaque? T)
                StorageKind.Opaque;
            else
                StorageKind.Composite (list)
        case pointer
            let innerT = ('element@ T 0)
            copy
                (this-function (Symbol (get-typename innerT bindings)) innerT bindings) . storage
            # if (type-builtin? innerT)
            #     StorageKind.Pointer (tostring innerT)
            # elseif ('function-pointer? T)
            #     let retT = ('return-type innerT)
            #     let farglist = (list (get-typename retT bindings))
            #     StorageKind.Function
            #         fold (args = farglist) for el in ('elements innerT)
            #             el as:= type
            #             args .. (list (get-typename el bindings))

            # else
            #     let tname =
            #         try
            #             'get bindings.typename-lookup (hash innerT)
            #         except (ex)
            #             error "opaque pointer to unknown typename"
            #     StorageKind.Pointer (tostring tname)
        default
            StorageKind.Opaque;

    let TS =
        TypeStorage
            name = sym
            storage = stkind

    'set bindings.defined-types sym (copy TS)
    'append bindings.storages (copy TS)
    TS

fn import-bindings (includestr opt)
    sc_import_c "bindings.c" includestr opt (Scope)

fn gen-bindings-object (includestr opt filter)
    local bindings = (HeaderBindings)
    let header = (import-bindings includestr opt)

    # collect typenames
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let match? start end = ('match? filter (k as string))
                if match?
                    let T = (v as type)
                    'set bindings.typenames (k as Symbol) (hash T)
                    'set bindings.typename-lookup (hash T) (k as Symbol)
        _ 'typedef 'enum 'struct 'union
    # recursively define types
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let match? start end = ('match? filter (k as string))
                if match?
                    let T = (v as type)
                    walk-type (k as Symbol) T bindings
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
