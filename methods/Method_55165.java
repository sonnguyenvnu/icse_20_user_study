/** 
 * Unsafe version of:  {@link #objc_getMetaClass} 
 */
public static long nobjc_getMetaClass(long name){
  long __functionAddress=Functions.objc_getMetaClass;
  return invokePP(name,__functionAddress);
}
