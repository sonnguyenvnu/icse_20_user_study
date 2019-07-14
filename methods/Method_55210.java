/** 
 * Returns a string describing a method's return type.
 * @param m the method to inspect
 * @return a C string describing the return type. You must free the string with free().
 */
@Nullable @NativeType("char *") public static String method_copyReturnType(@NativeType("Method") long m){
  long __result=nmethod_copyReturnType(m);
  return memUTF8Safe(__result);
}
