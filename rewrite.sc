using import struct
using import Map
using import Array
using import enum
using import Rc

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

fn type-builtin? (T)
    va-lfold false
        inline (__ curT result)
            result or (T == curT)
        _ i8 u8 i16 u16 i32 u32 i64 u64 f32 f64 bool

enum StorageKind
    Pointer : (mutable? = bool) (T = Symbol)
    FunctionPointer : (retT = Symbol) (params = (Array Symbol))
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
    storages : (Array (Rc TypeStorage))
    # easy lookup, has to be set whenever storages is appended.
    storage-lookup : (Map Symbol (Rc TypeStorage))

    fn get-typename (self T)
        # prefer to use builtin typenames when possible, otherwise
        # it'll just use the last defined alias, which isn't good.
        if (type-builtin? T)
            Symbol (tostring T)
        else
            try
                'get self.typename-lookup (hash T)
            else
                # FIXME: generate typenames when necessary
                'Unknown

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
            return;
        else
            # go on, then
            ;

        let TS =
            :: finish
            if ('function-pointer? T)
                # unwrap FP into `function', then get the return type
                let f = ('element@ ('storageof T) 0)
                let retT = ('return-type f)
                let ret-sym = ('get-typename self retT)
                this-function self ret-sym retT

                local params : (Array Symbol)
                for param in ('elements f)
                    let p-sym = ('get-typename self param)
                    this-function self p-sym param
                    'append params p-sym
                merge finish
                    TypeStorage sym
                        StorageKind.FunctionPointer
                            retT = ret-sym
                            params = (deref params)
            TypeStorage 'Unknown
                StorageKind.TypeReference
                    'Unknown

            finish ::
        TS := (Rc.wrap TS)
        'append self.storages (copy TS)
        'set self.storage-lookup TS.name (copy TS)
        ;
        # TypeStorage sym (StorageKind.TypeReference sym)

fn import-bindings (includestr opt)
    sc_import_c "bindings.c" includestr opt (Scope)

fn gen-bindings-object (includestr opt filter)
    local bindings = (HeaderBindings)
    let header = (import-bindings includestr opt)

    # insert builtin types first as references
    va-map
        inline (T)
            'add-typename bindings (Symbol (tostring T)) T
        _ i8 u8 i16 u16 i32 u32 i64 u64 f32 f64 bool

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
