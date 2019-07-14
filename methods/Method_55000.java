/** 
 * Returns the next  {@code unsigned int} argument.
 * @param args the function arguments
 */
@NativeType("DCint") public static int dcbArgUInt(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgUInt(args);
}
