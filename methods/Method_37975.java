/** 
 * Returns <code>true</code> if string contains only digits.
 */
public static boolean containsOnlyDigits(final CharSequence string){
  int size=string.length();
  for (int i=0; i < size; i++) {
    char c=string.charAt(i);
    if (!CharUtil.isDigit(c)) {
      return false;
    }
  }
  return true;
}
