/** 
 * Unsafe version of:  {@link #protocol_getName} 
 */
public static long nprotocol_getName(long p){
  long __functionAddress=Functions.protocol_getName;
  if (CHECKS) {
    check(p);
  }
  return invokePP(p,__functionAddress);
}
