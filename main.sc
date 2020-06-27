using import radlib.string-utils
using import enum
using import Map
using import Array

let header = (include "GLFW/glfw3.h")
let filter = "^(?!GLFW)"

enum DefinitionException plain
    UNEXPECTED_ARGUMENT
    DEPENDS_ON_UNKNOWN_TYPE

global known-typenames : (Map type Symbol)
global defined-types : (Map type Symbol)
global anonymous-types : (Array type)
fn gen-type-definition (T)
    returning string
    if (T == void)
        return " void "

    if (('opaque? T) and (not (T < function)))
        try
            return (.. " " (tostring ('get known-typenames T)) " ")
        else (raise DefinitionException.UNEXPECTED_ARGUMENT)
    match ('superof T)
    case CStruct
        let storage =
            try
                'storageof T
            else
                report T
                raise DefinitionException.UNEXPECTED_ARGUMENT
        # we can't raise Error so we need to use this form instead of the Generator
        # directly.
        let element-count =
            try ('element-count T)
            else (raise DefinitionException.UNEXPECTED_ARGUMENT)

        .. "(tuple"
            fold (def = "") for i in (range element-count)
                let el =
                    try
                        'element@ storage i
                    else
                        report T
                        raise DefinitionException.UNEXPECTED_ARGUMENT
                let k eT = ('keyof el)
                try
                    let knownT = ('get defined-types eT)
                    .. def
                        interpolate "(${k} = ${knownT})"
                else
                    let storage =
                        try
                            'storageof eT
                        else
                            report T
                            raise DefinitionException.UNEXPECTED_ARGUMENT
                    if (storage < tuple)
                        raise DefinitionException.DEPENDS_ON_UNKNOWN_TYPE
                    interpolate "(${k} = ${this-function eT})"
            ")"
    case CEnum
        try
            tostring ('storageof T)
        else
            report T
            raise DefinitionException.UNEXPECTED_ARGUMENT
    case CUnion
        ""
    case pointer
        let prefix =
            ? ('writable? T) "mutable@" "pointer"
        let innerT =
            try ('element@ T 0)
            else (raise DefinitionException.UNEXPECTED_ARGUMENT)
        interpolate "(${prefix}${this-function innerT})"
    case function
        let retT = ('return-type T)
        let element-count =
            try ('element-count T)
            else (raise DefinitionException.UNEXPECTED_ARGUMENT)

        ..
            "(function"
            interpolate "${this-function retT}"
            fold (def = "") for i in (range element-count)
                let el =
                    try
                        'element@ T i
                    else
                        print T
                        raise DefinitionException.UNEXPECTED_ARGUMENT
                try
                    let knownT = ('get defined-types el)
                    .. def " " (tostring knownT) " "
                else
                    let storage =
                        try
                            'storageof el
                        else
                            print T
                            raise DefinitionException.UNEXPECTED_ARGUMENT
                    if (storage < tuple)
                        raise DefinitionException.DEPENDS_ON_UNKNOWN_TYPE
                    .. def
                        this-function el
            ")"
    case integer
        .. " " (tostring T) " "
    case real
        .. " " (tostring T) " "
    case array
        tostring T
    default
        report T ('superof T)
        raise DefinitionException.UNEXPECTED_ARGUMENT

# The first priority for correct bindings is to declare every type
# that will be used in functions and as parts of other types.
# First, we make typenames (forward declarations) for every named type in the struct, enum and
# union namespaces. At this point we keep track of the typenames we already know about.


print "\# struct"
for k v in header.struct
    if ('match? filter (k as Symbol as string))
        continue;
    'set known-typenames (v as type) (k as Symbol)
    io-write!
        interpolate
            """"let ${k} = (typename "${v}" CStruct)

print "\# union"
for k v in header.union
    if ('match? filter (k as Symbol as string))
        continue;
    'set known-typenames (v as type) (k as Symbol)
    io-write!
        interpolate
            """"let ${k} = (typename "${v}" CUnion)

print "\# enum"
for k v in header.enum
    if ('match? filter (k as Symbol as string))
        continue;
    'set known-typenames (v as type) (k as Symbol)
    io-write!
        interpolate
            """"let ${k} = (typename "${v}" CEnum)

# Because we kept track of those typenames, we refer to them in the typedefs.
# If a known typename is encountered, most of the time this will result on a Symbol
# resolving to itself, but will also cover the case where a type is typedef'd to another.
print "\# typedef"
for k v in header.typedef
    if ('match? filter (k as Symbol as string))
        continue;
    v as:= type
    try
        let original-decl = ('get known-typenames v)
        io-write!
            interpolate
                """"let ${k} = ${original-decl}
    else
        let superT = ('superof v)
        match superT
        case (or CStruct CUnion CEnum pointer typename)
            'set known-typenames v (k as Symbol)
            io-write!
                interpolate
                    """"let ${k} = (typename "${k}" ${superT})
        default
            io-write!
                interpolate
                    """"let ${k} = ${v}

# Now we start defining the storage for each type. In order to make sure dependencies are
# resolved, we will iterate multiple times on a map of known storages so far.
io-write!
    """"inline function-pointer-rimply (lhs rhs)
            static-if (lhs == Closure)
                inline (self)
                    imply self (storageof rhs)
for k v in known-typenames
    let T = (k as type)
    let name = (v as Symbol)
    if ('opaque? T)
        'set defined-types T name
        continue;

    let fp? = ('function-pointer? T)
    try
        io-write!
            interpolate
                """"'set-plain-storage ${name}
                        ${gen-type-definition T}
        if fp?
            io-write!
                interpolate
                    """"'set-symbol ${name} '__rimply function-pointer-rimply

        'set defined-types T name
    except (ex)
        switch ex
        case DefinitionException.UNEXPECTED_ARGUMENT
            hide-traceback;
            error (.. "wrong argument " (tostring T))
        default
            continue;

for k v in header.extern
    k as:= Symbol
    let T = ('element@ ('typeof v) 0)
    try
        io-write!
            interpolate
                """"let ${k} = (extern '${k} ${gen-type-definition T})
    except (ex)
        switch ex
        case DefinitionException.UNEXPECTED_ARGUMENT
            hide-traceback;
            error (.. "wrong argument " (tostring T))
        default
            continue;

print "locals;"
