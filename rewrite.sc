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
    Pointer
    Composite : Scope
    Native : string
    Opaque

struct TypeStorage
    name : Symbol
    storage : StorageKind

struct HeaderBindings
    typenames : (Map hash Symbol)
    defined-types : (Map hash StorageKind)
    # topologically sorted array of storage types
    storages : (Array TypeStorage)

fn type-builtin? (T)
    """"Test whether T is one of the default language types.
    va-lfold true
        inline (__ current computed)
            computed and (T == current)
        _ i8 u8 i16 u16 i32 u32 i64 u64 f16 f32 f64

fn walk-type (sym T bindings)
    let storages = bindings.storages
    # try
    #     # have we defined this already?
    #     'get bindings.defined-types sym
    #     return;
    # else
    #     # go on, then
    #     ;
    # native type?
    let super = ('superof T)
    match super
    case (or real integer)
    case CStruct
        ;
    default
        ;


fn import-bindings (includestr opt)
    sc_import_c "bindings.c" includestr opt (Scope)

fn gen-bindings-object (includestr opt filter)
    local bindings = (HeaderBindings)
    let header = (import-bindings includestr opt)
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let match? start end = ('match? filter (k as string))
                if match?
                    let T = (v as type)
                    'set bindings.typenames (hash T) (k as Symbol)
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
