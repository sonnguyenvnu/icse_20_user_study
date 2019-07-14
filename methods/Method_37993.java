/** 
 * Checks if string starts with at least one string from the provided array. If at least one string is matched, it returns its index. Otherwise, <code>-1</code> is returned.
 */
public static int startsWithOne(final String src,final String... dest){
  for (int i=0; i < dest.length; i++) {
    String m=dest[i];
    if (m == null) {
      continue;
    }
    if (src.startsWith(m)) {
      return i;
    }
  }
  return -1;
}
