/** 
 * Returns the next  {@code float} argument.
 * @param args the function arguments
 */
@NativeType("DCfloat") public static float dcbArgFloat(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgFloat(args);
}
