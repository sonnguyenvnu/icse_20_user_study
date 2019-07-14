/** 
 * Trim whitespaces from the right.
 */
public static String trimRight(final String src){
  int len=src.length();
  int count=len;
  while ((len > 0) && (CharUtil.isWhitespace(src.charAt(len - 1)))) {
    len--;
  }
  return (len < count) ? src.substring(0,len) : src;
}
