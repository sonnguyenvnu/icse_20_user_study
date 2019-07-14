/** 
 * Returns the next  {@code char} argument.
 * @param args the function arguments
 */
@NativeType("DCchar") public static byte dcbArgChar(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgChar(args);
}
