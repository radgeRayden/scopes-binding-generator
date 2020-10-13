using import radlib.stringtools

fn import-bindings (includestr opt)
    sc_import_c "bindings.c" includestr opt (Scope)

fn emit-bindings-JSON (includestr opt)

fn emit-bindings-stdout (includestr opt)

do
    let emit-bindings-JSON emit-bindings-stdout
    locals;
