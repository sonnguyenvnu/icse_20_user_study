/** 
 * Returns the name of a method. <p>To get the method name as a C string, call  {@code sel_getName(method_getName(method))}.</p>
 * @param m the method to inspect
 * @return a pointer of type SEL
 */
@NativeType("SEL") public static long method_getName(@NativeType("Method") long m){
  long __functionAddress=Functions.method_getName;
  if (CHECKS) {
    check(m);
  }
  return invokePP(m,__functionAddress);
}
