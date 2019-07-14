/** 
 * Returns the next  {@code short} argument.
 * @param args the function arguments
 */
@NativeType("DCshort") public static short dcbArgShort(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgShort(args);
}
