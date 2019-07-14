/** 
 * Detects quote character or return 0.
 */
public static char detectQuoteChar(final String str){
  if (str.length() < 2) {
    return 0;
  }
  final char c=str.charAt(0);
  if (c != str.charAt(str.length() - 1)) {
    return 0;
  }
  if (c == '\'' || c == '"' || c == '`') {
    return c;
  }
  return 0;
}
