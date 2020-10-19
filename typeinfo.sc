using import struct
using import Map
using import Array
using import enum
using import Rc

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
        _ i8 u8 i16 u16 i32 u32 i64 u64 f32 f64 bool void

enum StorageKind
    Pointer : (mutable? = bool) (T = Symbol)
    FunctionPointer : (retT = Symbol) (params = (Array Symbol))
    Tuple : (Array (tuple (field-name = Symbol) (T = Symbol)))
    Enum : (Array (tuple (field-name = Symbol) (constant = i32)))
    Union : (Array (tuple (variant = Symbol) (T = Symbol)))
    TypeReference : Symbol
    Opaque
    # to detect cycles, we append the type initially in a pending state.
    Pending

struct TypeStorage
    name : Symbol
    storage : StorageKind

struct Typename
    name : Symbol
    super : Symbol

    inline __hash (self)
        hash (hash self.name) (hash self.super)

    inline __== (cls other-cls)
        static-if ((cls == this-type) and (other-cls == this-type))
            inline (a b)
                and
                    a.name == b.name
                    a.super == b.super

struct HeaderTypeInfo
    # is a Map to enforce no collisions
    typenames : (Map Typename hash)
    typename-sym-lookup : (Map Symbol Typename)
    typename-type-lookup : (Map hash Typename)
    # topologically sorted array of storage types
    storages : (Array (Rc TypeStorage))
    # easy lookup, has to be set whenever storages is appended.
    storage-lookup : (Map Symbol (Rc TypeStorage))
    functions : (Array TypeStorage)

    fn get-typename (self T)
        returning Symbol
        # prefer to use builtin typenames when possible, otherwise
        # it'll just use the last defined alias, which isn't good.
        if (type-builtin? T)
            Symbol (tostring T)
        else
            try
                dupe (('get self.typename-type-lookup (hash T)) . name)
            else
                # TODO: generate all typenames we need
                if ('pointer? T)
                    let prefix = (? ('writable? T) "mutable@" "@")
                    let innerT = (this-function self ('element@ T 0))
                    Symbol f"${prefix}<${innerT}>"
                else
                    'Unknown

    fn add-typename (self sym T)
        let super = (Symbol (tostring ('superof T)))
        'set self.typenames (Typename sym super) (hash T)
        'set self.typename-type-lookup (hash T) (Typename sym super)
        'set self.typename-sym-lookup sym (Typename sym super)
        ;

    fn add-storage (self sym T)
        raising Error
        # have we defined this already?
        # we must lookup by symbol because a lot of typedefs are aliases to
        # builtin types.
        if ('in? self.storage-lookup sym)
            try
                # TODO: if storage is pending, it means we're in a cycle. Might
                # be useful to indicate here that it needs a fwd declaration.
                'get self.storage-lookup sym
                return;
            else
                # go on, then
                ;

        'set self.storage-lookup sym
            Rc.wrap
                TypeStorage sym
                    StorageKind.Pending;

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

            if ('pointer? T)
                let innerT = ('element@ T 0)
                let innerT-sym = ('get-typename self innerT)
                this-function self innerT-sym innerT
                merge finish
                    TypeStorage sym
                        StorageKind.Pointer ('writable? T) innerT-sym

            if ('opaque? T)
                merge finish
                    TypeStorage sym
                        StorageKind.Opaque;

            if (type-builtin? T)
                merge finish
                    TypeStorage sym
                        StorageKind.TypeReference (Symbol (tostring T))

            let super = ('superof T)
            match super
            case CStruct
                let FieldArray = (elementof StorageKind.Tuple.Type 0)
                local fields : FieldArray
                for el in ('elements T)
                    let name fT = ('keyof el)
                    let fT-name = ('get-typename self fT)
                    this-function self fT-name fT
                    'append fields
                        tupleof
                            field-name = name
                            T = fT-name
                TypeStorage sym
                    StorageKind.Tuple (deref fields)
            case CEnum
                let FieldArray = (elementof StorageKind.Enum.Type 0)
                local fields : FieldArray
                for k v in ('symbols T)
                    'append fields
                        tupleof
                            field-name = k
                            constant = ((sc_const_int_extract v) as i32)
                TypeStorage sym
                    StorageKind.Enum (deref fields)
            case CUnion
                error "not yet implemented"
            default
                error (.. "unknown type kind: " (tostring T) " < " (tostring super))
            finish ::
        TS := (Rc.wrap TS)
        'append self.storages (copy TS)
        'set self.storage-lookup TS.name (copy TS)
        ;

    fn add-function (self sym T)
        if (not ('function-pointer? T))
            error f"type ${T} is not a function"

        let f = ('element@ ('storageof T) 0)
        let retT = ('return-type f)
        let ret-sym = ('get-typename self retT)
        'add-storage self ret-sym retT

        local params : (Array Symbol)
        for param in ('elements f)
            let p-sym = ('get-typename self param)
            'add-storage self p-sym param
            'append params p-sym

        'append self.functions
            TypeStorage sym
                StorageKind.FunctionPointer
                    retT = ret-sym
                    params = (deref params)
        ;

fn gen-header-type-info (include-scope)
    local bindings = (HeaderTypeInfo)
    let header = include-scope

    # insert builtin types first as references
    va-map
        inline (T)
            let sym = (Symbol (tostring T))
            'set bindings.storage-lookup sym
                Rc.wrap
                    TypeStorage sym (StorageKind.TypeReference sym)
        _ i8 u8 i16 u16 i32 u32 i64 u64 f32 f64 bool void

    # collect typenames
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let T = (v as type)
                'add-typename bindings k T
        _ 'typedef 'enum 'struct 'union
    # recursively define types
    va-map
        inline (subscope)
            for k v in (('@ header subscope) as Scope)
                k as:= Symbol
                let T = (v as type)
                'add-storage bindings (k as Symbol) T
        _ 'typedef 'enum 'struct 'union

    for k v in (('@ header 'extern) as Scope)
        k as:= Symbol
        let T = ('typeof v)
        'add-function bindings k T

    bindings

do
    let gen-header-type-info TypeStorage StorageKind HeaderTypeInfo Typename
    locals;
