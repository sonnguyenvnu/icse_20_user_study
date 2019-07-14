/** 
 * Compares string with at least one from the provided array. If at least one equal string is found, returns its index. Otherwise, <code>-1</code> is returned.
 */
public static int equalsOne(final String src,final String... dest){
  for (int i=0; i < dest.length; i++) {
    if (src.equals(dest[i])) {
      return i;
    }
  }
  return -1;
}
