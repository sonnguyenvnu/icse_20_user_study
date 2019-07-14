/** 
 * Calculate the offset of the first non-whitespace character in a string.
 * @param str the string to examine
 * @return offset of first non-whitespace character in str
 */
protected static int nonWhiteSpaceOffset(String str){
  for (int i=0; i < str.length(); i++) {
    if (!Character.isWhitespace(str.charAt(i))) {
      return i;
    }
  }
  return str.length() - 1;
}
