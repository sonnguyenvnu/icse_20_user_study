/** 
 * Unsafe version of:  {@link #objc_getProtocol} 
 */
public static long nobjc_getProtocol(long name){
  long __functionAddress=Functions.objc_getProtocol;
  return invokePP(name,__functionAddress);
}
