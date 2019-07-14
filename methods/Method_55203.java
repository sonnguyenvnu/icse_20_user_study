/** 
 * Unsafe version of:  {@link #objc_constructInstance} 
 */
public static long nobjc_constructInstance(long cls,long bytes){
  long __functionAddress=Functions.objc_constructInstance;
  return invokePPP(cls,bytes,__functionAddress);
}
