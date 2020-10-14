using import struct
using import Map
using import Array

import .cjson
using import radlib.stringtools
using import itertools

struct HeaderBindings
    typenames : (Map Symbol type)

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
                    'set bindings.typenames k (v as type)
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
