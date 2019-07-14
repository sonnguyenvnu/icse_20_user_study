/** 
 * Compares string with at least one from the provided array, ignoring case. If at least one equal string is found, it returns its index. Otherwise, <code>-1</code> is returned.
 */
public static int equalsOneIgnoreCase(final String src,final String... dest){
  for (int i=0; i < dest.length; i++) {
    if (src.equalsIgnoreCase(dest[i])) {
      return i;
    }
  }
  return -1;
}
