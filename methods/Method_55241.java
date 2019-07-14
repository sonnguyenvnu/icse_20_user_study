/** 
 * Unsafe version of:  {@link #sel_getUid} 
 */
public static long nsel_getUid(long str){
  long __functionAddress=Functions.sel_getUid;
  return invokePP(str,__functionAddress);
}
