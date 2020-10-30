# generator.sc
# --------------------------------------------------------------------------------

import .cjson
import .typeinfo

fn emit-storage-definition (storage-array TS tinfo)
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
    case TypeReference (ref)
        cjson.AddStringToObject stdef "kind" "alias"
        cjson.AddStringToObject stdef "type" (tostring ref)
    case Opaque ()
        cjson.AddStringToObject stdef "kind" "opaque"
    default
        error (.. "serialization not yet implemented: " (tostring TS.storage))

    cjson.AddItemToArray storage-array stdef

fn emit-function-definition (function-array TS tinfo)
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

fn emit-constant-definition (constant-array name initializer tinfo)
    let const-def = (cjson.CreateObject)
    cjson.AddStringToObject const-def "name" (tostring name)
    cjson.AddStringToObject const-def "type" (tostring initializer.type)

    let args = (cjson.AddArrayToObject const-def "args")
    for arg in initializer.args
        let obj = (cjson.CreateObject)
        cjson.AddStringToObject obj "type" (tostring arg.type)
        let const = (extractvalue arg 1)

        dispatch const
        case Int (v)
            # we serialize as string to avoid precision issues
            cjson.AddStringToObject obj "value" (dec v)
        case Real (v)
            cjson.AddNumberToObject obj "value" v
        default
            error "NYI"

        cjson.AddItemToArray args obj
    cjson.AddItemToArray constant-array const-def

fn gen-bindings-JSON (scope)
    let metadata = (typeinfo.gen-header-type-info scope)
    let bindings = (cjson.CreateObject)
    let typenames = (cjson.AddObjectToObject bindings "typenames")
    let storages = (cjson.AddArrayToObject bindings "storages")
    let externs = (cjson.AddArrayToObject bindings "externs")
    let defines = (cjson.AddArrayToObject bindings "defines")

    for tname in metadata.typenames
        cjson.AddStringToObject typenames (tostring tname.name) (tostring tname.super)

    for ts in metadata.storages
        emit-storage-definition storages ts metadata

    for ts in metadata.functions
        emit-function-definition externs ts metadata

    for k v in metadata.constants
        emit-constant-definition defines k v metadata

    bindings

locals;
