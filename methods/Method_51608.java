/** 
 * Returns true if both arrays are if both are null or have zero-length, otherwise return the false if their respective elements are not equal by position.
 * @param < T >
 * @param a
 * @param b
 * @return boolean
 * @deprecated {@link Arrays#deepEquals(Object[],Object[])}
 */
@Deprecated public static <T>boolean areSemanticEquals(T[] a,T[] b){
  if (a == null) {
    return b == null || b.length == 0;
  }
  if (b == null) {
    return a.length == 0;
  }
  if (a.length != b.length) {
    return false;
  }
  for (int i=0; i < a.length; i++) {
    if (!areEqual(a[i],b[i])) {
      return false;
    }
  }
  return true;
}
