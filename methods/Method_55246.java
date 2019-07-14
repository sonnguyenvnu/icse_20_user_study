/** 
 * Unsafe version of:  {@link #objc_loadWeak} 
 */
public static long nobjc_loadWeak(long location){
  long __functionAddress=Functions.objc_loadWeak;
  return invokePP(location,__functionAddress);
}
