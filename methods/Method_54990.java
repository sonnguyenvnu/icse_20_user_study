/** 
 * Returns the size, in bytes, of the specified struct.
 * @param s the struct
 */
@NativeType("DCsize") public static long dcStructSize(@NativeType("DCstruct *") long s){
  if (CHECKS) {
    check(s);
  }
  return ndcStructSize(s);
}
