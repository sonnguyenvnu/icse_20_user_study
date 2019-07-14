/** 
 * Returns <code>true</code> if string contains only white spaces.
 */
public static boolean containsOnlyWhitespaces(final CharSequence string){
  int size=string.length();
  for (int i=0; i < size; i++) {
    char c=string.charAt(i);
    if (!CharUtil.isWhitespace(c)) {
      return false;
    }
  }
  return true;
}
