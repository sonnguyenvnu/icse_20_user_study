/** 
 * Exchanges the implementations of two methods.
 * @param m1 the method to exchange with second method
 * @param m2 the method to exchange with first method
 */
public static void method_exchangeImplementations(@NativeType("Method") long m1,@NativeType("Method") long m2){
  long __functionAddress=Functions.method_exchangeImplementations;
  if (CHECKS) {
    check(m1);
    check(m2);
  }
  invokePPV(m1,m2,__functionAddress);
}
