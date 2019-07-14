/** 
 * Sets the implementation of a method.
 * @param m   the method for which to set an implementation
 * @param imp the implemention to set to this method
 * @return the previous implementation of the method
 */
@NativeType("IMP") public static long method_setImplementation(@NativeType("Method") long m,@NativeType("IMP") long imp){
  long __functionAddress=Functions.method_setImplementation;
  if (CHECKS) {
    check(m);
    check(imp);
  }
  return invokePPP(m,imp,__functionAddress);
}
