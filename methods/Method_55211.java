/** 
 * Returns a string describing a single parameter type of a method.
 * @param m     the method to inspect
 * @param index the index of the parameter to inspect
 * @return a C string describing the type of the parameter at index {@code index}, or  {@code NULL} if method has no parameter index {@code index}. You must free the string with free().
 */
@Nullable @NativeType("char *") public static String method_copyArgumentType(@NativeType("Method") long m,@NativeType("unsigned int") int index){
  long __result=nmethod_copyArgumentType(m,index);
  return memUTF8Safe(__result);
}
