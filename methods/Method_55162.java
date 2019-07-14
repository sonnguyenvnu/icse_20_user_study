/** 
 * Unsafe version of:  {@link #objc_getClass} 
 */
public static long nobjc_getClass(long name){
  long __functionAddress=Functions.objc_getClass;
  return invokePP(name,__functionAddress);
}
