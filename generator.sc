# generator.sc
# --------------------------------------------------------------------------------

import .cjson
import .typeinfo

fn add-storage-definition (storage-array TS tinfo)
    let stdef = (cjson.CreateObject)
    cjson.AddStringToObject stdef "typename" (tostring TS.name)
    dispatch TS.storage
    case Pointer (mutable? T)
        cjson.AddStringToObject stdef "kind" "pointer"
        cjson.AddStringToObject stdef "type" (tostring T)
        cjson.AddBoolToObject stdef "mutable?" mutable?
    case FunctionPointer (retT params)
        cjson.AddStringToObject stdef "kind" "function-pointer"
        cjson.AddStringToObject stdef "return-type" (tostring retT)
        let _params = (cjson.AddArrayToObject stdef "parameters")
        for p in params
            cjson.AddItemToArray _params
                cjson.CreateString (tostring p)
    case Tuple (fields)
        cjson.AddStringToObject stdef "kind" "struct"
        let _fields = (cjson.AddArrayToObject stdef "fields")
        for f in fields
            let field = (cjson.CreateObject)
            cjson.AddStringToObject field "name" (tostring f.field-name)
            cjson.AddStringToObject field "type" (tostring f.T)
            cjson.AddItemToArray _fields field
    case Enum (fields)
        cjson.AddStringToObject stdef "kind" "enum"
        let _fields = (cjson.AddArrayToObject stdef "fields")
        for f in fields
            let field = (cjson.CreateObject)
            cjson.AddStringToObject field "name" (tostring f.field-name)
            cjson.AddStringToObject field "constant" (tostring f.constant)
            cjson.AddItemToArray _fields field
    case Union (fields)
        error "not yet implemented"
    case Opaque ()
        cjson.AddStringToObject stdef "kind" "opaque"
    default
        unreachable;

    cjson.AddItemToArray storage-array stdef

fn add-function-definition (function-array TS tinfo)
    let stdef = (cjson.CreateObject)
    cjson.AddStringToObject stdef "name" (tostring TS.name)
    cjson.AddStringToObject stdef "kind" "function-pointer"

    let SK = typeinfo.StorageKind
    # FIXME: I think these can be other kinds of globals as well
    assert (('literal TS.storage) == SK.FunctionPointer.Literal)
    let retT params = ('unsafe-extract-payload TS.storage SK.FunctionPointer.Type)
    cjson.AddStringToObject stdef "return-type" (tostring retT)
    let _params = (cjson.AddArrayToObject stdef "parameters")
    for p in params
        cjson.AddItemToArray _params
            cjson.CreateString (tostring p)
    cjson.AddItemToArray function-array stdef

fn gen-bindings-JSON (scope)
    let metadata = (typeinfo.gen-header-type-info scope)
    let bindings = (cjson.CreateObject)
    let typenames = (cjson.AddArrayToObject bindings "typenames")
    let storages = (cjson.AddArrayToObject bindings "storages")
    let externs = (cjson.AddArrayToObject bindings "externs")
    for tname in metadata.typenames
        cjson.AddItemToArray typenames (cjson.CreateString ((tostring tname.name) as rawstring))

    for ts in metadata.storages
        add-storage-definition storages ts metadata

    for ts in metadata.functions
        add-function-definition externs ts metadata

    bindings

locals;
