/** 
 * Unsafe version of:  {@link #sel_getName} 
 */
public static long nsel_getName(long sel){
  long __functionAddress=Functions.sel_getName;
  if (CHECKS) {
    check(sel);
  }
  return invokePP(sel,__functionAddress);
}
