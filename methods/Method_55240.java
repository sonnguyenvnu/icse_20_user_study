/** 
 * Returns the name of the method specified by a given selector.
 * @param sel a pointer of type SEL. Pass the selector whose name you wish to determine.
 * @return a C string indicating the name of the selector
 */
@Nullable @NativeType("char const *") public static String sel_getName(@NativeType("SEL") long sel){
  long __result=nsel_getName(sel);
  return memUTF8Safe(__result);
}
