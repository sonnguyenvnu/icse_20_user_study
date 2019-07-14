/** 
 * Unsafe version of:  {@link #objc_allocateClassPair} 
 */
public static long nobjc_allocateClassPair(long superclass,long name,long extraBytes){
  long __functionAddress=Functions.objc_allocateClassPair;
  return invokePPPP(superclass,name,extraBytes,__functionAddress);
}
