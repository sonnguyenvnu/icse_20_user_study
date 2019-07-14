/** 
 * Unsafe version of:  {@link #objc_lookUpClass} 
 */
public static long nobjc_lookUpClass(long name){
  long __functionAddress=Functions.objc_lookUpClass;
  return invokePP(name,__functionAddress);
}
