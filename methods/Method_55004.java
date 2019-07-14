/** 
 * Returns the next  {@code double} argument.
 * @param args the function arguments
 */
@NativeType("DCdouble") public static double dcbArgDouble(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgDouble(args);
}
