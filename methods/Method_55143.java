/** 
 * Provides diagnostic information corresponding to problems with calls to  {@link #dlopen},  {@link #dlsym}, and  {@link #dlclose} in the same thread.<p>When there's a problem to report, this function returns a pointer to a null-terminated string describing the problem. Otherwise, this function returns {@code NULL}.</p> <p>Each call to  {@code dlerror} resets its diagnostic buffer. If a program needs to keep a record of past error messages, it must store them itself.Subsequent calls to  {@code dlerror} in the same thread with no calls to {@link #dlopen},  {@link #dlsym}, or  {@link #dlclose}, return  {@code NULL}.</p>
 */
@Nullable @NativeType("char const *") public static String dlerror(){
  long __result=ndlerror();
  return memASCIISafe(__result);
}
