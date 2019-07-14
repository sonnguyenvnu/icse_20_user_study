/** 
 * Returns the next  {@code unsigned char} argument.
 * @param args the function arguments
 */
@NativeType("DCchar") public static byte dcbArgUChar(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgUChar(args);
}
