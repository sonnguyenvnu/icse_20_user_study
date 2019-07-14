/** 
 * Returns first index of a whitespace character, starting from specified index offset.
 */
public static int indexOfWhitespace(final String string,final int startindex,final int endindex){
  for (int i=startindex; i < endindex; i++) {
    if (CharUtil.isWhitespace(string.charAt(i))) {
      return i;
    }
  }
  return -1;
}
