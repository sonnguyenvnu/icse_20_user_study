/** 
 * Returns the symbol name exported by the specified library at the specified index.
 * @param pSyms a {@code DLSyms} object
 */
@Nullable @NativeType("char const *") public static String dlSymsName(@NativeType("DLSyms *") long pSyms,int index){
  if (CHECKS) {
    check(pSyms);
  }
  long __result=ndlSymsName(pSyms,index);
  return memASCIISafe(__result);
}
