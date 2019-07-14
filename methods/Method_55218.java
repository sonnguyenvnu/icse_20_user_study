/** 
 * Unsafe version of:  {@link #ivar_getName} 
 */
public static long nivar_getName(long v){
  long __functionAddress=Functions.ivar_getName;
  if (CHECKS) {
    check(v);
  }
  return invokePP(v,__functionAddress);
}
