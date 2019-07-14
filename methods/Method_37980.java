/** 
 * Returns <code>true</code> if substring exist at given offset in a string.
 */
public static boolean isSubstringAt(final String string,final String substring,final int offset){
  int len=substring.length();
  int max=offset + len;
  if (max > string.length()) {
    return false;
  }
  int ndx=0;
  for (int i=offset; i < max; i++, ndx++) {
    if (string.charAt(i) != substring.charAt(ndx)) {
      return false;
    }
  }
  return true;
}
