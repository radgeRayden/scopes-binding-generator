typedef SCGenPointer < pointer
    inline __rimply (A B)
        inline (self other)
            imply self (storageof B)

let type-buffer = (alloca-array type 128)
let GLFWwindowiconifyfun = (sc_typename_type "GLFWwindowiconifyfun" SCGenPointer)
let GLFWwindow = (sc_typename_type "GLFWwindow" CStruct)
let GLFWerrorfun = (sc_typename_type "GLFWerrorfun" SCGenPointer)
let GLFWcursorposfun = (sc_typename_type "GLFWcursorposfun" SCGenPointer)
let GLFWscrollfun = (sc_typename_type "GLFWscrollfun" SCGenPointer)
let GLFWglproc = (sc_typename_type "GLFWglproc" SCGenPointer)
let GLFWcursor = (sc_typename_type "GLFWcursor" CStruct)
let GLFWwindowfocusfun = (sc_typename_type "GLFWwindowfocusfun" SCGenPointer)
let GLFWwindowclosefun = (sc_typename_type "GLFWwindowclosefun" SCGenPointer)
let GLFWcursorenterfun = (sc_typename_type "GLFWcursorenterfun" SCGenPointer)
let GLFWgammaramp = (sc_typename_type "GLFWgammaramp" CStruct)
let GLFWvkproc = (sc_typename_type "GLFWvkproc" SCGenPointer)
let GLFWwindowmaximizefun = (sc_typename_type "GLFWwindowmaximizefun" SCGenPointer)
let GLFWmousebuttonfun = (sc_typename_type "GLFWmousebuttonfun" SCGenPointer)
let GLFWvidmode = (sc_typename_type "GLFWvidmode" CStruct)
let GLFWwindowposfun = (sc_typename_type "GLFWwindowposfun" SCGenPointer)
let GLFWgamepadstate = (sc_typename_type "GLFWgamepadstate" CStruct)
let GLFWwindowsizefun = (sc_typename_type "GLFWwindowsizefun" SCGenPointer)
let GLFWimage = (sc_typename_type "GLFWimage" CStruct)
let GLFWframebuffersizefun = (sc_typename_type "GLFWframebuffersizefun" SCGenPointer)
let GLFWcharmodsfun = (sc_typename_type "GLFWcharmodsfun" SCGenPointer)
let GLFWcharfun = (sc_typename_type "GLFWcharfun" SCGenPointer)
let GLFWkeyfun = (sc_typename_type "GLFWkeyfun" SCGenPointer)
let GLFWwindowcontentscalefun = (sc_typename_type "GLFWwindowcontentscalefun" SCGenPointer)
let GLFWmonitor = (sc_typename_type "GLFWmonitor" CStruct)
let GLFWmonitorfun = (sc_typename_type "GLFWmonitorfun" SCGenPointer)
let GLFWjoystickfun = (sc_typename_type "GLFWjoystickfun" SCGenPointer)
let GLFWdropfun = (sc_typename_type "GLFWdropfun" SCGenPointer)
let GLFWwindowrefreshfun = (sc_typename_type "GLFWwindowrefreshfun" SCGenPointer)
sc_typename_type_set_opaque GLFWmonitor
sc_typename_type_set_opaque GLFWwindow
sc_typename_type_set_opaque GLFWcursor
store (sc_key_type 'width i32) (getelementptr type-buffer 0)
store (sc_key_type 'height i32) (getelementptr type-buffer 1)
store (sc_key_type 'redBits i32) (getelementptr type-buffer 2)
store (sc_key_type 'greenBits i32) (getelementptr type-buffer 3)
store (sc_key_type 'blueBits i32) (getelementptr type-buffer 4)
store (sc_key_type 'refreshRate i32) (getelementptr type-buffer 5)
sc_typename_type_set_storage GLFWvidmode (sc_tuple_type 6 type-buffer) typename-flag-plain
let _gensc_mutable@<u16> = (sc_pointer_type u16 18446744073709551613:u64 unnamed)
store (sc_key_type 'red _gensc_mutable@<u16>) (getelementptr type-buffer 0)
store (sc_key_type 'green _gensc_mutable@<u16>) (getelementptr type-buffer 1)
store (sc_key_type 'blue _gensc_mutable@<u16>) (getelementptr type-buffer 2)
store (sc_key_type 'size u32) (getelementptr type-buffer 3)
sc_typename_type_set_storage GLFWgammaramp (sc_tuple_type 4 type-buffer) typename-flag-plain
let _gensc_mutable@<u8> = (sc_pointer_type u8 18446744073709551613:u64 unnamed)
store (sc_key_type 'width i32) (getelementptr type-buffer 0)
store (sc_key_type 'height i32) (getelementptr type-buffer 1)
store (sc_key_type 'pixels _gensc_mutable@<u8>) (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWimage (sc_tuple_type 3 type-buffer) typename-flag-plain
let _gensc_array<u8_15> = (sc_array_type u8 15)
let _gensc_array<f32_6> = (sc_array_type f32 6)
store (sc_key_type 'buttons _gensc_array<u8_15>) (getelementptr type-buffer 0)
store (sc_key_type 'axes _gensc_array<f32_6>) (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWgamepadstate (sc_tuple_type 2 type-buffer) typename-flag-plain
sc_typename_type_set_storage GLFWglproc (sc_pointer_type (sc_function_type void 0 type-buffer) 2:u64 unnamed) typename-flag-plain
sc_typename_type_set_storage GLFWvkproc (sc_pointer_type (sc_function_type void 0 type-buffer) 2:u64 unnamed) typename-flag-plain
let _gensc_@<i8> = (sc_pointer_type i8 2:u64 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWerrorfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
let _gensc_mutable@<GLFWwindow> = (sc_pointer_type GLFWwindow 18446744073709551613:u64 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWwindowposfun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWwindowsizefun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
sc_typename_type_set_storage GLFWwindowclosefun (sc_pointer_type (sc_function_type void 1 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
sc_typename_type_set_storage GLFWwindowrefreshfun (sc_pointer_type (sc_function_type void 1 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWwindowfocusfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWwindowiconifyfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWwindowmaximizefun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWframebuffersizefun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store f32 (getelementptr type-buffer 1)
store f32 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWwindowcontentscalefun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
sc_typename_type_set_storage GLFWmousebuttonfun (sc_pointer_type (sc_function_type void 4 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store f64 (getelementptr type-buffer 1)
store f64 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWcursorposfun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWcursorenterfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store f64 (getelementptr type-buffer 1)
store f64 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWscrollfun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
store i32 (getelementptr type-buffer 4)
sc_typename_type_set_storage GLFWkeyfun (sc_pointer_type (sc_function_type void 5 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWcharfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWcharmodsfun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
let _gensc_mutable@<_gensc_@<i8>> = (sc_pointer_type _gensc_@<i8> 18446744073709551613:u64 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store _gensc_mutable@<_gensc_@<i8>> (getelementptr type-buffer 2)
sc_typename_type_set_storage GLFWdropfun (sc_pointer_type (sc_function_type void 3 type-buffer) 2:u64 unnamed) typename-flag-plain
let _gensc_mutable@<GLFWmonitor> = (sc_pointer_type GLFWmonitor 18446744073709551613:u64 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWmonitorfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store i32 (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
sc_typename_type_set_storage GLFWjoystickfun (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
let _gensc_mutable@<i32> = (sc_pointer_type i32 18446744073709551613:u64 unnamed)
let _gensc_mutable@<_gensc_mutable@<GLFWmonitor>> = (sc_pointer_type _gensc_mutable@<GLFWmonitor> 18446744073709551613:u64 unnamed)
let _gensc_mutable@<f32> = (sc_pointer_type f32 18446744073709551613:u64 unnamed)
let _gensc_@<void> = (sc_pointer_type void 2:u64 unnamed)
let _gensc_@<GLFWvidmode> = (sc_pointer_type GLFWvidmode 2:u64 unnamed)
let _gensc_@<GLFWgammaramp> = (sc_pointer_type GLFWgammaramp 2:u64 unnamed)
let _gensc_@<GLFWimage> = (sc_pointer_type GLFWimage 2:u64 unnamed)
let _gensc_mutable@<f64> = (sc_pointer_type f64 18446744073709551613:u64 unnamed)
let _gensc_mutable@<GLFWcursor> = (sc_pointer_type GLFWcursor 18446744073709551613:u64 unnamed)
let _gensc_@<f32> = (sc_pointer_type f32 2:u64 unnamed)
let _gensc_@<u8> = (sc_pointer_type u8 2:u64 unnamed)
let _gensc_mutable@<GLFWgamepadstate> = (sc_pointer_type GLFWgamepadstate 18446744073709551613:u64 unnamed)
let _gensc_mutable@<u32> = (sc_pointer_type u32 18446744073709551613:u64 unnamed)
let glfwInit = (sc_global_new 'glfwInit (sc_function_type i32 0 type-buffer) 6 unnamed)
let glfwTerminate = (sc_global_new 'glfwTerminate (sc_function_type void 0 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwInitHint = (sc_global_new 'glfwInitHint (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<i32> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
let glfwGetVersion = (sc_global_new 'glfwGetVersion (sc_function_type void 3 type-buffer) 6 unnamed)
let glfwGetVersionString = (sc_global_new 'glfwGetVersionString (sc_function_type _gensc_@<i8> 0 type-buffer) 6 unnamed)
store _gensc_mutable@<_gensc_@<i8>> (getelementptr type-buffer 0)
let glfwGetError = (sc_global_new 'glfwGetError (sc_function_type i32 1 type-buffer) 6 unnamed)
store GLFWerrorfun (getelementptr type-buffer 0)
let glfwSetErrorCallback = (sc_global_new 'glfwSetErrorCallback (sc_function_type GLFWerrorfun 1 type-buffer) 6 unnamed)
store _gensc_mutable@<i32> (getelementptr type-buffer 0)
let glfwGetMonitors = (sc_global_new 'glfwGetMonitors (sc_function_type _gensc_mutable@<_gensc_mutable@<GLFWmonitor>> 1 type-buffer) 6 unnamed)
let glfwGetPrimaryMonitor = (sc_global_new 'glfwGetPrimaryMonitor (sc_function_type _gensc_mutable@<GLFWmonitor> 0 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
let glfwGetMonitorPos = (sc_global_new 'glfwGetMonitorPos (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
store _gensc_mutable@<i32> (getelementptr type-buffer 3)
store _gensc_mutable@<i32> (getelementptr type-buffer 4)
let glfwGetMonitorWorkarea = (sc_global_new 'glfwGetMonitorWorkarea (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
let glfwGetMonitorPhysicalSize = (sc_global_new 'glfwGetMonitorPhysicalSize (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_mutable@<f32> (getelementptr type-buffer 1)
store _gensc_mutable@<f32> (getelementptr type-buffer 2)
let glfwGetMonitorContentScale = (sc_global_new 'glfwGetMonitorContentScale (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
let glfwGetMonitorName = (sc_global_new 'glfwGetMonitorName (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let glfwSetMonitorUserPointer = (sc_global_new 'glfwSetMonitorUserPointer (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
let glfwGetMonitorUserPointer = (sc_global_new 'glfwGetMonitorUserPointer (sc_function_type _gensc_@<void> 1 type-buffer) 6 unnamed)
store GLFWmonitorfun (getelementptr type-buffer 0)
let glfwSetMonitorCallback = (sc_global_new 'glfwSetMonitorCallback (sc_function_type GLFWmonitorfun 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
let glfwGetVideoModes = (sc_global_new 'glfwGetVideoModes (sc_function_type _gensc_@<GLFWvidmode> 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
let glfwGetVideoMode = (sc_global_new 'glfwGetVideoMode (sc_function_type _gensc_@<GLFWvidmode> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store f32 (getelementptr type-buffer 1)
let glfwSetGamma = (sc_global_new 'glfwSetGamma (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
let glfwGetGammaRamp = (sc_global_new 'glfwGetGammaRamp (sc_function_type _gensc_@<GLFWgammaramp> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 0)
store _gensc_@<GLFWgammaramp> (getelementptr type-buffer 1)
let glfwSetGammaRamp = (sc_global_new 'glfwSetGammaRamp (sc_function_type void 2 type-buffer) 6 unnamed)
let glfwDefaultWindowHints = (sc_global_new 'glfwDefaultWindowHints (sc_function_type void 0 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwWindowHint = (sc_global_new 'glfwWindowHint (sc_function_type void 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
let glfwWindowHintString = (sc_global_new 'glfwWindowHintString (sc_function_type void 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store _gensc_@<i8> (getelementptr type-buffer 2)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 3)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 4)
let glfwCreateWindow = (sc_global_new 'glfwCreateWindow (sc_function_type _gensc_mutable@<GLFWwindow> 5 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwDestroyWindow = (sc_global_new 'glfwDestroyWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwWindowShouldClose = (sc_global_new 'glfwWindowShouldClose (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwSetWindowShouldClose = (sc_global_new 'glfwSetWindowShouldClose (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
let glfwSetWindowTitle = (sc_global_new 'glfwSetWindowTitle (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store _gensc_@<GLFWimage> (getelementptr type-buffer 2)
let glfwSetWindowIcon = (sc_global_new 'glfwSetWindowIcon (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
let glfwGetWindowPos = (sc_global_new 'glfwGetWindowPos (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let glfwSetWindowPos = (sc_global_new 'glfwSetWindowPos (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
let glfwGetWindowSize = (sc_global_new 'glfwGetWindowSize (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
store i32 (getelementptr type-buffer 4)
let glfwSetWindowSizeLimits = (sc_global_new 'glfwSetWindowSizeLimits (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let glfwSetWindowAspectRatio = (sc_global_new 'glfwSetWindowAspectRatio (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let glfwSetWindowSize = (sc_global_new 'glfwSetWindowSize (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
let glfwGetFramebufferSize = (sc_global_new 'glfwGetFramebufferSize (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
store _gensc_mutable@<i32> (getelementptr type-buffer 2)
store _gensc_mutable@<i32> (getelementptr type-buffer 3)
store _gensc_mutable@<i32> (getelementptr type-buffer 4)
let glfwGetWindowFrameSize = (sc_global_new 'glfwGetWindowFrameSize (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<f32> (getelementptr type-buffer 1)
store _gensc_mutable@<f32> (getelementptr type-buffer 2)
let glfwGetWindowContentScale = (sc_global_new 'glfwGetWindowContentScale (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwGetWindowOpacity = (sc_global_new 'glfwGetWindowOpacity (sc_function_type f32 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store f32 (getelementptr type-buffer 1)
let glfwSetWindowOpacity = (sc_global_new 'glfwSetWindowOpacity (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwIconifyWindow = (sc_global_new 'glfwIconifyWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwRestoreWindow = (sc_global_new 'glfwRestoreWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwMaximizeWindow = (sc_global_new 'glfwMaximizeWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwShowWindow = (sc_global_new 'glfwShowWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwHideWindow = (sc_global_new 'glfwHideWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwFocusWindow = (sc_global_new 'glfwFocusWindow (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwRequestWindowAttention = (sc_global_new 'glfwRequestWindowAttention (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwGetWindowMonitor = (sc_global_new 'glfwGetWindowMonitor (sc_function_type _gensc_mutable@<GLFWmonitor> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<GLFWmonitor> (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
store i32 (getelementptr type-buffer 4)
store i32 (getelementptr type-buffer 5)
store i32 (getelementptr type-buffer 6)
let glfwSetWindowMonitor = (sc_global_new 'glfwSetWindowMonitor (sc_function_type void 7 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwGetWindowAttrib = (sc_global_new 'glfwGetWindowAttrib (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let glfwSetWindowAttrib = (sc_global_new 'glfwSetWindowAttrib (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let glfwSetWindowUserPointer = (sc_global_new 'glfwSetWindowUserPointer (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwGetWindowUserPointer = (sc_global_new 'glfwGetWindowUserPointer (sc_function_type _gensc_@<void> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWframebuffersizefun (getelementptr type-buffer 1)
let glfwSetWindowPosCallback = (sc_global_new 'glfwSetWindowPosCallback (sc_function_type GLFWframebuffersizefun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWframebuffersizefun (getelementptr type-buffer 1)
let glfwSetWindowSizeCallback = (sc_global_new 'glfwSetWindowSizeCallback (sc_function_type GLFWframebuffersizefun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWwindowrefreshfun (getelementptr type-buffer 1)
let glfwSetWindowCloseCallback = (sc_global_new 'glfwSetWindowCloseCallback (sc_function_type GLFWwindowrefreshfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWwindowrefreshfun (getelementptr type-buffer 1)
let glfwSetWindowRefreshCallback = (sc_global_new 'glfwSetWindowRefreshCallback (sc_function_type GLFWwindowrefreshfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWcursorenterfun (getelementptr type-buffer 1)
let glfwSetWindowFocusCallback = (sc_global_new 'glfwSetWindowFocusCallback (sc_function_type GLFWcursorenterfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWcursorenterfun (getelementptr type-buffer 1)
let glfwSetWindowIconifyCallback = (sc_global_new 'glfwSetWindowIconifyCallback (sc_function_type GLFWcursorenterfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWcursorenterfun (getelementptr type-buffer 1)
let glfwSetWindowMaximizeCallback = (sc_global_new 'glfwSetWindowMaximizeCallback (sc_function_type GLFWcursorenterfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWframebuffersizefun (getelementptr type-buffer 1)
let glfwSetFramebufferSizeCallback = (sc_global_new 'glfwSetFramebufferSizeCallback (sc_function_type GLFWframebuffersizefun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWwindowcontentscalefun (getelementptr type-buffer 1)
let glfwSetWindowContentScaleCallback = (sc_global_new 'glfwSetWindowContentScaleCallback (sc_function_type GLFWwindowcontentscalefun 2 type-buffer) 6 unnamed)
let glfwPollEvents = (sc_global_new 'glfwPollEvents (sc_function_type void 0 type-buffer) 6 unnamed)
let glfwWaitEvents = (sc_global_new 'glfwWaitEvents (sc_function_type void 0 type-buffer) 6 unnamed)
store f64 (getelementptr type-buffer 0)
let glfwWaitEventsTimeout = (sc_global_new 'glfwWaitEventsTimeout (sc_function_type void 1 type-buffer) 6 unnamed)
let glfwPostEmptyEvent = (sc_global_new 'glfwPostEmptyEvent (sc_function_type void 0 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwGetInputMode = (sc_global_new 'glfwGetInputMode (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let glfwSetInputMode = (sc_global_new 'glfwSetInputMode (sc_function_type void 3 type-buffer) 6 unnamed)
let glfwRawMouseMotionSupported = (sc_global_new 'glfwRawMouseMotionSupported (sc_function_type i32 0 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwGetKeyName = (sc_global_new 'glfwGetKeyName (sc_function_type _gensc_@<i8> 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwGetKeyScancode = (sc_global_new 'glfwGetKeyScancode (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwGetKey = (sc_global_new 'glfwGetKey (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let glfwGetMouseButton = (sc_global_new 'glfwGetMouseButton (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<f64> (getelementptr type-buffer 1)
store _gensc_mutable@<f64> (getelementptr type-buffer 2)
let glfwGetCursorPos = (sc_global_new 'glfwGetCursorPos (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store f64 (getelementptr type-buffer 1)
store f64 (getelementptr type-buffer 2)
let glfwSetCursorPos = (sc_global_new 'glfwSetCursorPos (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<GLFWimage> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let glfwCreateCursor = (sc_global_new 'glfwCreateCursor (sc_function_type _gensc_mutable@<GLFWcursor> 3 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwCreateStandardCursor = (sc_global_new 'glfwCreateStandardCursor (sc_function_type _gensc_mutable@<GLFWcursor> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWcursor> (getelementptr type-buffer 0)
let glfwDestroyCursor = (sc_global_new 'glfwDestroyCursor (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_mutable@<GLFWcursor> (getelementptr type-buffer 1)
let glfwSetCursor = (sc_global_new 'glfwSetCursor (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWkeyfun (getelementptr type-buffer 1)
let glfwSetKeyCallback = (sc_global_new 'glfwSetKeyCallback (sc_function_type GLFWkeyfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWcharfun (getelementptr type-buffer 1)
let glfwSetCharCallback = (sc_global_new 'glfwSetCharCallback (sc_function_type GLFWcharfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWcharmodsfun (getelementptr type-buffer 1)
let glfwSetCharModsCallback = (sc_global_new 'glfwSetCharModsCallback (sc_function_type GLFWcharmodsfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWmousebuttonfun (getelementptr type-buffer 1)
let glfwSetMouseButtonCallback = (sc_global_new 'glfwSetMouseButtonCallback (sc_function_type GLFWmousebuttonfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWscrollfun (getelementptr type-buffer 1)
let glfwSetCursorPosCallback = (sc_global_new 'glfwSetCursorPosCallback (sc_function_type GLFWscrollfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWcursorenterfun (getelementptr type-buffer 1)
let glfwSetCursorEnterCallback = (sc_global_new 'glfwSetCursorEnterCallback (sc_function_type GLFWcursorenterfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWscrollfun (getelementptr type-buffer 1)
let glfwSetScrollCallback = (sc_global_new 'glfwSetScrollCallback (sc_function_type GLFWscrollfun 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store GLFWdropfun (getelementptr type-buffer 1)
let glfwSetDropCallback = (sc_global_new 'glfwSetDropCallback (sc_function_type GLFWdropfun 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwJoystickPresent = (sc_global_new 'glfwJoystickPresent (sc_function_type i32 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
let glfwGetJoystickAxes = (sc_global_new 'glfwGetJoystickAxes (sc_function_type _gensc_@<f32> 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
let glfwGetJoystickButtons = (sc_global_new 'glfwGetJoystickButtons (sc_function_type _gensc_@<u8> 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
let glfwGetJoystickHats = (sc_global_new 'glfwGetJoystickHats (sc_function_type _gensc_@<u8> 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwGetJoystickName = (sc_global_new 'glfwGetJoystickName (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwGetJoystickGUID = (sc_global_new 'glfwGetJoystickGUID (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let glfwSetJoystickUserPointer = (sc_global_new 'glfwSetJoystickUserPointer (sc_function_type void 2 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwGetJoystickUserPointer = (sc_global_new 'glfwGetJoystickUserPointer (sc_function_type _gensc_@<void> 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwJoystickIsGamepad = (sc_global_new 'glfwJoystickIsGamepad (sc_function_type i32 1 type-buffer) 6 unnamed)
store GLFWjoystickfun (getelementptr type-buffer 0)
let glfwSetJoystickCallback = (sc_global_new 'glfwSetJoystickCallback (sc_function_type GLFWjoystickfun 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let glfwUpdateGamepadMappings = (sc_global_new 'glfwUpdateGamepadMappings (sc_function_type i32 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwGetGamepadName = (sc_global_new 'glfwGetGamepadName (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
store _gensc_mutable@<GLFWgamepadstate> (getelementptr type-buffer 1)
let glfwGetGamepadState = (sc_global_new 'glfwGetGamepadState (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
let glfwSetClipboardString = (sc_global_new 'glfwSetClipboardString (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwGetClipboardString = (sc_global_new 'glfwGetClipboardString (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
let glfwGetTime = (sc_global_new 'glfwGetTime (sc_function_type f64 0 type-buffer) 6 unnamed)
store f64 (getelementptr type-buffer 0)
let glfwSetTime = (sc_global_new 'glfwSetTime (sc_function_type void 1 type-buffer) 6 unnamed)
let glfwGetTimerValue = (sc_global_new 'glfwGetTimerValue (sc_function_type u64 0 type-buffer) 6 unnamed)
let glfwGetTimerFrequency = (sc_global_new 'glfwGetTimerFrequency (sc_function_type u64 0 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwMakeContextCurrent = (sc_global_new 'glfwMakeContextCurrent (sc_function_type void 1 type-buffer) 6 unnamed)
let glfwGetCurrentContext = (sc_global_new 'glfwGetCurrentContext (sc_function_type _gensc_mutable@<GLFWwindow> 0 type-buffer) 6 unnamed)
store _gensc_mutable@<GLFWwindow> (getelementptr type-buffer 0)
let glfwSwapBuffers = (sc_global_new 'glfwSwapBuffers (sc_function_type void 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let glfwSwapInterval = (sc_global_new 'glfwSwapInterval (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let glfwExtensionSupported = (sc_global_new 'glfwExtensionSupported (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let glfwGetProcAddress = (sc_global_new 'glfwGetProcAddress (sc_function_type GLFWvkproc 1 type-buffer) 6 unnamed)
let glfwVulkanSupported = (sc_global_new 'glfwVulkanSupported (sc_function_type i32 0 type-buffer) 6 unnamed)
store _gensc_mutable@<u32> (getelementptr type-buffer 0)
let glfwGetRequiredInstanceExtensions = (sc_global_new 'glfwGetRequiredInstanceExtensions (sc_function_type _gensc_mutable@<_gensc_@<i8>> 1 type-buffer) 6 unnamed)
do
    let windowiconifyfun = GLFWwindowiconifyfun
    let window = GLFWwindow
    let errorfun = GLFWerrorfun
    let cursorposfun = GLFWcursorposfun
    let scrollfun = GLFWscrollfun
    let glproc = GLFWglproc
    let cursor = GLFWcursor
    let windowfocusfun = GLFWwindowfocusfun
    let windowclosefun = GLFWwindowclosefun
    let cursorenterfun = GLFWcursorenterfun
    let gammaramp = GLFWgammaramp
    let vkproc = GLFWvkproc
    let windowmaximizefun = GLFWwindowmaximizefun
    let mousebuttonfun = GLFWmousebuttonfun
    let vidmode = GLFWvidmode
    let windowposfun = GLFWwindowposfun
    let gamepadstate = GLFWgamepadstate
    let windowsizefun = GLFWwindowsizefun
    let image = GLFWimage
    let framebuffersizefun = GLFWframebuffersizefun
    let charmodsfun = GLFWcharmodsfun
    let charfun = GLFWcharfun
    let keyfun = GLFWkeyfun
    let windowcontentscalefun = GLFWwindowcontentscalefun
    let monitor = GLFWmonitor
    let monitorfun = GLFWmonitorfun
    let joystickfun = GLFWjoystickfun
    let dropfun = GLFWdropfun
    let windowrefreshfun = GLFWwindowrefreshfun
    let Init = glfwInit
    let Terminate = glfwTerminate
    let InitHint = glfwInitHint
    let GetVersion = glfwGetVersion
    let GetVersionString = glfwGetVersionString
    let GetError = glfwGetError
    let SetErrorCallback = glfwSetErrorCallback
    let GetMonitors = glfwGetMonitors
    let GetPrimaryMonitor = glfwGetPrimaryMonitor
    let GetMonitorPos = glfwGetMonitorPos
    let GetMonitorWorkarea = glfwGetMonitorWorkarea
    let GetMonitorPhysicalSize = glfwGetMonitorPhysicalSize
    let GetMonitorContentScale = glfwGetMonitorContentScale
    let GetMonitorName = glfwGetMonitorName
    let SetMonitorUserPointer = glfwSetMonitorUserPointer
    let GetMonitorUserPointer = glfwGetMonitorUserPointer
    let SetMonitorCallback = glfwSetMonitorCallback
    let GetVideoModes = glfwGetVideoModes
    let GetVideoMode = glfwGetVideoMode
    let SetGamma = glfwSetGamma
    let GetGammaRamp = glfwGetGammaRamp
    let SetGammaRamp = glfwSetGammaRamp
    let DefaultWindowHints = glfwDefaultWindowHints
    let WindowHint = glfwWindowHint
    let WindowHintString = glfwWindowHintString
    let CreateWindow = glfwCreateWindow
    let DestroyWindow = glfwDestroyWindow
    let WindowShouldClose = glfwWindowShouldClose
    let SetWindowShouldClose = glfwSetWindowShouldClose
    let SetWindowTitle = glfwSetWindowTitle
    let SetWindowIcon = glfwSetWindowIcon
    let GetWindowPos = glfwGetWindowPos
    let SetWindowPos = glfwSetWindowPos
    let GetWindowSize = glfwGetWindowSize
    let SetWindowSizeLimits = glfwSetWindowSizeLimits
    let SetWindowAspectRatio = glfwSetWindowAspectRatio
    let SetWindowSize = glfwSetWindowSize
    let GetFramebufferSize = glfwGetFramebufferSize
    let GetWindowFrameSize = glfwGetWindowFrameSize
    let GetWindowContentScale = glfwGetWindowContentScale
    let GetWindowOpacity = glfwGetWindowOpacity
    let SetWindowOpacity = glfwSetWindowOpacity
    let IconifyWindow = glfwIconifyWindow
    let RestoreWindow = glfwRestoreWindow
    let MaximizeWindow = glfwMaximizeWindow
    let ShowWindow = glfwShowWindow
    let HideWindow = glfwHideWindow
    let FocusWindow = glfwFocusWindow
    let RequestWindowAttention = glfwRequestWindowAttention
    let GetWindowMonitor = glfwGetWindowMonitor
    let SetWindowMonitor = glfwSetWindowMonitor
    let GetWindowAttrib = glfwGetWindowAttrib
    let SetWindowAttrib = glfwSetWindowAttrib
    let SetWindowUserPointer = glfwSetWindowUserPointer
    let GetWindowUserPointer = glfwGetWindowUserPointer
    let SetWindowPosCallback = glfwSetWindowPosCallback
    let SetWindowSizeCallback = glfwSetWindowSizeCallback
    let SetWindowCloseCallback = glfwSetWindowCloseCallback
    let SetWindowRefreshCallback = glfwSetWindowRefreshCallback
    let SetWindowFocusCallback = glfwSetWindowFocusCallback
    let SetWindowIconifyCallback = glfwSetWindowIconifyCallback
    let SetWindowMaximizeCallback = glfwSetWindowMaximizeCallback
    let SetFramebufferSizeCallback = glfwSetFramebufferSizeCallback
    let SetWindowContentScaleCallback = glfwSetWindowContentScaleCallback
    let PollEvents = glfwPollEvents
    let WaitEvents = glfwWaitEvents
    let WaitEventsTimeout = glfwWaitEventsTimeout
    let PostEmptyEvent = glfwPostEmptyEvent
    let GetInputMode = glfwGetInputMode
    let SetInputMode = glfwSetInputMode
    let RawMouseMotionSupported = glfwRawMouseMotionSupported
    let GetKeyName = glfwGetKeyName
    let GetKeyScancode = glfwGetKeyScancode
    let GetKey = glfwGetKey
    let GetMouseButton = glfwGetMouseButton
    let GetCursorPos = glfwGetCursorPos
    let SetCursorPos = glfwSetCursorPos
    let CreateCursor = glfwCreateCursor
    let CreateStandardCursor = glfwCreateStandardCursor
    let DestroyCursor = glfwDestroyCursor
    let SetCursor = glfwSetCursor
    let SetKeyCallback = glfwSetKeyCallback
    let SetCharCallback = glfwSetCharCallback
    let SetCharModsCallback = glfwSetCharModsCallback
    let SetMouseButtonCallback = glfwSetMouseButtonCallback
    let SetCursorPosCallback = glfwSetCursorPosCallback
    let SetCursorEnterCallback = glfwSetCursorEnterCallback
    let SetScrollCallback = glfwSetScrollCallback
    let SetDropCallback = glfwSetDropCallback
    let JoystickPresent = glfwJoystickPresent
    let GetJoystickAxes = glfwGetJoystickAxes
    let GetJoystickButtons = glfwGetJoystickButtons
    let GetJoystickHats = glfwGetJoystickHats
    let GetJoystickName = glfwGetJoystickName
    let GetJoystickGUID = glfwGetJoystickGUID
    let SetJoystickUserPointer = glfwSetJoystickUserPointer
    let GetJoystickUserPointer = glfwGetJoystickUserPointer
    let JoystickIsGamepad = glfwJoystickIsGamepad
    let SetJoystickCallback = glfwSetJoystickCallback
    let UpdateGamepadMappings = glfwUpdateGamepadMappings
    let GetGamepadName = glfwGetGamepadName
    let GetGamepadState = glfwGetGamepadState
    let SetClipboardString = glfwSetClipboardString
    let GetClipboardString = glfwGetClipboardString
    let GetTime = glfwGetTime
    let SetTime = glfwSetTime
    let GetTimerValue = glfwGetTimerValue
    let GetTimerFrequency = glfwGetTimerFrequency
    let MakeContextCurrent = glfwMakeContextCurrent
    let GetCurrentContext = glfwGetCurrentContext
    let SwapBuffers = glfwSwapBuffers
    let SwapInterval = glfwSwapInterval
    let ExtensionSupported = glfwExtensionSupported
    let GetProcAddress = glfwGetProcAddress
    let VulkanSupported = glfwVulkanSupported
    let GetRequiredInstanceExtensions = glfwGetRequiredInstanceExtensions
    let PRESS = 1
    let KEY_LEFT_SUPER = 343
    let KEY_F13 = 302
    let KEY_MENU = 348
    let FOCUS_ON_SHOW = 131084
    let MAXIMIZED = 131080
    let JOYSTICK_6 = 5
    let KEY_Z = 90
    let KEY_LEFT_ALT = 342
    let JOYSTICK_13 = 12
    let KEY_9 = 57
    let KEY_BACKSPACE = 259
    let KEY_LAST = 348
    let GAMEPAD_AXIS_LEFT_TRIGGER = 4
    let STICKY_MOUSE_BUTTONS = 208899
    let KEY_F = 70
    let KEY_4 = 52
    let RAW_MOUSE_MOTION = 208901
    let KEY_Y = 89
    let INVALID_ENUM = 65539
    let KEY_N = 78
    let KEY_SEMICOLON = 59
    let KEY_F9 = 298
    let OPENGL_ES_API = 196610
    let HOVERED = 131083
    let KEY_F11 = 300
    let JOYSTICK_15 = 14
    let KEY_UNKNOWN = 340282366920938463463374607431768211455
    let KEY_KP_0 = 320
    let CROSSHAIR_CURSOR = 221187
    let KEY_2 = 50
    let FLOATING = 131079
    let MOUSE_BUTTON_7 = 6
    let MOUSE_BUTTON_RIGHT = 1
    let OUT_OF_MEMORY = 65541
    let KEY_E = 69
    let KEY_RIGHT_BRACKET = 93
    let NO_RESET_NOTIFICATION = 200705
    let KEY_KP_9 = 329
    let JOYSTICK_9 = 8
    let KEY_F21 = 310
    let GAMEPAD_BUTTON_TRIANGLE = 3
    let ACCUM_BLUE_BITS = 135177
    let CENTER_CURSOR = 131081
    let OPENGL_CORE_PROFILE = 204801
    let KEY_Q = 81
    let KEY_S = 83
    let ARROW_CURSOR = 221185
    let GAMEPAD_BUTTON_LAST = 14
    let KEY_F10 = 299
    let FORMAT_UNAVAILABLE = 65545
    let KEY_V = 86
    let KEY_APOSTROPHE = 39
    let JOYSTICK_5 = 4
    let DONT_CARE = 340282366920938463463374607431768211455
    let CONTEXT_NO_ERROR = 139274
    let MOD_ALT = 4
    let JOYSTICK_16 = 15
    let GAMEPAD_BUTTON_SQUARE = 2
    let KEY_RIGHT = 262
    let KEY_F25 = 314
    let HAND_CURSOR = 221188
    let KEY_GRAVE_ACCENT = 96
    let KEY_F4 = 293
    let JOYSTICK_2 = 1
    let PLATFORM_ERROR = 65544
    let MOD_CONTROL = 2
    let JOYSTICK_14 = 13
    let GAMEPAD_BUTTON_X = 2
    let SAMPLES = 135181
    let JOYSTICK_7 = 6
    let KEY_KP_2 = 322
    let DEPTH_BITS = 135173
    let MOUSE_BUTTON_5 = 4
    let KEY_ENTER = 257
    let KEY_RIGHT_SHIFT = 344
    let KEY_3 = 51
    let MOUSE_BUTTON_4 = 3
    let KEY_KP_SUBTRACT = 333
    let GAMEPAD_BUTTON_DPAD_LEFT = 14
    let VERSION_REVISION = 2
    let X11_INSTANCE_NAME = 147458
    let KEY_KP_4 = 324
    let GAMEPAD_BUTTON_LEFT_BUMPER = 4
    let KEY_B = 66
    let DOUBLEBUFFER = 135184
    let JOYSTICK_1 = 0
    let GAMEPAD_BUTTON_DPAD_UP = 11
    let CURSOR_NORMAL = 212993
    let KEY_F14 = 303
    let OPENGL_PROFILE = 139272
    let KEY_RIGHT_SUPER = 347
    let KEY_CAPS_LOCK = 280
    let CURSOR_DISABLED = 212995
    let KEY_RIGHT_ALT = 346
    let LOCK_KEY_MODS = 208900
    let KEY_F15 = 304
    let AUX_BUFFERS = 135179
    let KEY_0 = 48
    let GAMEPAD_BUTTON_LEFT_THUMB = 9
    let EGL_CONTEXT_API = 221186
    let GAMEPAD_BUTTON_DPAD_RIGHT = 12
    let KEY_F12 = 301
    let HAT_UP = 1
    let KEY_6 = 54
    let REFRESH_RATE = 135183
    let GAMEPAD_BUTTON_A = 0
    let GAMEPAD_BUTTON_BACK = 6
    let KEY_R = 82
    let KEY_8 = 56
    let LOSE_CONTEXT_ON_RESET = 200706
    let HAT_RIGHT = 2
    let MOD_CAPS_LOCK = 16
    let KEY_U = 85
    let KEY_PRINT_SCREEN = 283
    let MOD_NUM_LOCK = 32
    let GAMEPAD_AXIS_RIGHT_X = 2
    let MOUSE_BUTTON_LAST = 7
    let RELEASE_BEHAVIOR_FLUSH = 217089
    let OPENGL_DEBUG_CONTEXT = 139271
    let COCOA_FRAME_NAME = 143362
    let KEY_F16 = 305
    let SRGB_CAPABLE = 135182
    let KEY_D = 68
    let KEY_HOME = 268
    let COCOA_GRAPHICS_SWITCHING = 143363
    let KEY_END = 269
    let BLUE_BITS = 135171
    let CONTEXT_VERSION_MAJOR = 139266
    let MOUSE_BUTTON_LEFT = 0
    let KEY_NUM_LOCK = 282
    let KEY_KP_ENTER = 335
    let KEY_F22 = 311
    let ACCUM_ALPHA_BITS = 135178
    let MOUSE_BUTTON_2 = 1
    let STEREO = 135180
    let COCOA_MENUBAR = 331778
    let KEY_PAGE_DOWN = 267
    let VERSION_UNAVAILABLE = 65543
    let X11_CLASS_NAME = 147457
    let KEY_KP_ADD = 334
    let RELEASE = 0
    let KEY_KP_DECIMAL = 330
    let KEY_X = 88
    let KEY_I = 73
    let KEY_UP = 265
    let GREEN_BITS = 135170
    let REPEAT = 2
    let KEY_COMMA = 44
    let HRESIZE_CURSOR = 221189
    let KEY_KP_3 = 323
    let KEY_W = 87
    let MOUSE_BUTTON_3 = 2
    let ANY_RELEASE_BEHAVIOR = 0
    let JOYSTICK_8 = 7
    let CLIENT_API = 139265
    let ACCUM_RED_BITS = 135175
    let KEY_J = 74
    let HAT_CENTERED = 0
    let GAMEPAD_BUTTON_GUIDE = 8
    let HAT_DOWN = 4
    let MOUSE_BUTTON_1 = 0
    let KEY_KP_6 = 326
    let KEY_F7 = 296
    let KEY_SPACE = 32
    let KEY_M = 77
    let GAMEPAD_AXIS_LAST = 5
    let KEY_KP_1 = 321
    let GAMEPAD_AXIS_RIGHT_Y = 3
    let KEY_SLASH = 47
    let KEY_F8 = 297
    let RESIZABLE = 131075
    let VISIBLE = 131076
    let KEY_F1 = 290
    let COCOA_RETINA_FRAMEBUFFER = 143361
    let CONTEXT_RELEASE_BEHAVIOR = 139273
    let API_UNAVAILABLE = 65542
    let MOUSE_BUTTON_8 = 7
    let NATIVE_CONTEXT_API = 221185
    let GAMEPAD_BUTTON_RIGHT_THUMB = 10
    let GAMEPAD_BUTTON_START = 7
    let KEY_LEFT = 263
    let KEY_PAUSE = 284
    let KEY_7 = 55
    let MOUSE_BUTTON_6 = 5
    let KEY_L = 76
    let GAMEPAD_AXIS_LEFT_Y = 1
    let KEY_F18 = 307
    let GAMEPAD_BUTTON_CIRCLE = 1
    let OPENGL_COMPAT_PROFILE = 204802
    let KEY_BACKSLASH = 92
    let RED_BITS = 135169
    let KEY_WORLD_1 = 161
    let KEY_K = 75
    let KEY_F2 = 291
    let KEY_C = 67
    let KEY_LEFT_BRACKET = 91
    let KEY_WORLD_2 = 162
    let KEY_TAB = 258
    let DECORATED = 131077
    let TRUE = 1
    let KEY_INSERT = 260
    let OPENGL_API = 196609
    let MOD_SHIFT = 1
    let JOYSTICK_HAT_BUTTONS = 327681
    let VERSION_MAJOR = 3
    let STENCIL_BITS = 135174
    let GAMEPAD_BUTTON_B = 1
    let KEY_ESCAPE = 256
    let KEY_F17 = 306
    let HAT_LEFT = 8
    let KEY_KP_8 = 328
    let OPENGL_FORWARD_COMPAT = 139270
    let GAMEPAD_BUTTON_DPAD_DOWN = 13
    let MOD_SUPER = 8
    let NO_ROBUSTNESS = 0
    let KEY_KP_MULTIPLY = 332
    let CONNECTED = 262145
    let KEY_LEFT_SHIFT = 340
    let CONTEXT_REVISION = 139268
    let ALPHA_BITS = 135172
    let IBEAM_CURSOR = 221186
    let NO_ERROR = 0
    let KEY_LEFT_CONTROL = 341
    let KEY_F6 = 295
    let INVALID_VALUE = 65540
    let GAMEPAD_BUTTON_Y = 3
    let KEY_KP_DIVIDE = 331
    let KEY_DELETE = 261
    let ACCUM_GREEN_BITS = 135176
    let KEY_RIGHT_CONTROL = 345
    let KEY_G = 71
    let KEY_EQUAL = 61
    let JOYSTICK_LAST = 15
    let KEY_H = 72
    let KEY_P = 80
    let KEY_SCROLL_LOCK = 281
    let CURSOR_HIDDEN = 212994
    let ICONIFIED = 131074
    let KEY_F19 = 308
    let CURSOR = 208897
    let JOYSTICK_10 = 9
    let GAMEPAD_BUTTON_CROSS = 0
    let CONTEXT_ROBUSTNESS = 139269
    let KEY_F5 = 294
    let KEY_F20 = 309
    let SCALE_TO_MONITOR = 139276
    let DISCONNECTED = 262146
    let AUTO_ICONIFY = 131078
    let KEY_5 = 53
    let KEY_O = 79
    let NO_WINDOW_CONTEXT = 65546
    let KEY_MINUS = 45
    let FOCUSED = 131073
    let OPENGL_ANY_PROFILE = 0
    let KEY_1 = 49
    let KEY_KP_EQUAL = 336
    let JOYSTICK_11 = 10
    let STICKY_KEYS = 208898
    let KEY_F23 = 312
    let NO_API = 0
    let COCOA_CHDIR_RESOURCES = 331777
    let GAMEPAD_BUTTON_RIGHT_BUMPER = 5
    let KEY_F3 = 292
    let GAMEPAD_AXIS_LEFT_X = 0
    let GAMEPAD_AXIS_RIGHT_TRIGGER = 5
    let FALSE = 0
    let CONTEXT_CREATION_API = 139275
    let KEY_T = 84
    let OSMESA_CONTEXT_API = 221187
    let VRESIZE_CURSOR = 221190
    let KEY_KP_7 = 327
    let KEY_PAGE_UP = 266
    let KEY_PERIOD = 46
    let NO_CURRENT_CONTEXT = 65538
    let JOYSTICK_12 = 11
    let KEY_DOWN = 264
    let RELEASE_BEHAVIOR_NONE = 217090
    let JOYSTICK_3 = 2
    let MOUSE_BUTTON_MIDDLE = 2
    let KEY_F24 = 313
    let NOT_INITIALIZED = 65537
    let JOYSTICK_4 = 3
    let KEY_KP_5 = 325
    let TRANSPARENT_FRAMEBUFFER = 131082
    let KEY_A = 65
    let CONTEXT_VERSION_MINOR = 139267
    let VERSION_MINOR = 3
    locals;
