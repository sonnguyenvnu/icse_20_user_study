/** 
 * Returns a human readable string describing the most recent error that occurred from  {@link #dlopen},  {@link #dlsym} or {@link #dlclose} sincethe last call to  {@code dlerror()}. It returns  {@code NULL} if no errors have occurred since initialization or since it was last called.
 */
@Nullable @NativeType("char *") public static String dlerror(){
  long __result=ndlerror();
  return memASCIISafe(__result);
}
