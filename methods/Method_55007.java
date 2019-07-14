/** 
 * Returns the number of symbols exported by the specified library.
 * @param pSyms a {@code DLSyms} object
 */
public static int dlSymsCount(@NativeType("DLSyms *") long pSyms){
  if (CHECKS) {
    check(pSyms);
  }
  return ndlSymsCount(pSyms);
}
