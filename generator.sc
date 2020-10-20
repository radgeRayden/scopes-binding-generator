# generator.sc
# --------------------------------------------------------------------------------

import .cjson
import .typeinfo

fn gen-storage-definition (TS tinfo)

fn gen-bindings-JSON (scope)
    let metadata = (typeinfo.gen-header-type-info scope)
    let bindings = (cjson.CreateObject)
    let typenames = (cjson.AddArrayToObject bindings "typenames")
    for tname in metadata.typenames
        cjson.AddItemToArray typenames (cjson.CreateString ((tostring tname.name) as rawstring))
    bindings

locals;
