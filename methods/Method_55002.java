/** 
 * Returns the next  {@code unsigned long long} argument.
 * @param args the function arguments
 */
@NativeType("DClonglong") public static long dcbArgULongLong(@NativeType("DCArgs *") long args){
  if (CHECKS) {
    check(args);
  }
  return ndcbArgULongLong(args);
}
