/** 
 * Returns the next  {@code unsigned long} argument.
 * @param args the function arguments
 */
@NativeType("DClong") public static int dcbArgULong(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgULong(args);
}
