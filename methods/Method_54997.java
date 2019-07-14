/** 
 * Returns the next  {@code int} argument.
 * @param args the function arguments
 */
@NativeType("DCint") public static int dcbArgInt(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgInt(args);
}
