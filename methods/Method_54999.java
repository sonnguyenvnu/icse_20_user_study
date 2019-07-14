/** 
 * Returns the next  {@code unsigned short} argument.
 * @param args the function arguments
 */
@NativeType("DCshort") public static short dcbArgUShort(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgUShort(args);
}
