# generator.sc
# --------------------------------------------------------------------------------

import .cjson
import .typeinfo

fn add-storage-definition (storage-array TS tinfo)
    let stdef = (cjson.CreateObject)
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
    default
        ;

    cjson.AddItemToArray storage-array stdef

fn add-function-definition (function-array TS tinfo)

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
