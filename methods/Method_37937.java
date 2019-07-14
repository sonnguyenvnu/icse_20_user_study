/** 
 * Converts char digit into integer value. Accepts numeric chars (0 - 9) as well as letter (A-z).
 */
public static int parseDigit(final char digit){
  if ((digit >= '0') && (digit <= '9')) {
    return digit - '0';
  }
  if (CharUtil.isLowercaseAlpha(digit)) {
    return 10 + digit - 'a';
  }
  return 10 + digit - 'A';
}
