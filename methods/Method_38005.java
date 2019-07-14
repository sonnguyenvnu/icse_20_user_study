/** 
 * Trim whitespaces from the left.
 */
public static String trimLeft(final String src){
  int len=src.length();
  int st=0;
  while ((st < len) && (CharUtil.isWhitespace(src.charAt(st)))) {
    st++;
  }
  return st > 0 ? src.substring(st) : src;
}
