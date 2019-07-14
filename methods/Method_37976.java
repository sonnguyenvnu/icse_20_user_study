/** 
 * Returns <code>true</code> if string  {@link #containsOnlyDigits(CharSequence) contains only digits}or signs plus or minus.
 */
public static boolean containsOnlyDigitsAndSigns(final CharSequence string){
  int size=string.length();
  for (int i=0; i < size; i++) {
    char c=string.charAt(i);
    if ((!CharUtil.isDigit(c)) && (c != '-') && (c != '+')) {
      return false;
    }
  }
  return true;
}
