/** 
 * check the originalStr is contain the whitespace
 * @param originalStr
 * @return true if contain whitespace
 */
public static boolean isContainWhitespace(String originalStr){
  if (StringUtil.isBlank(originalStr)) {
    return true;
  }
  int strLen=originalStr.length();
  for (int i=0; i < strLen; i++) {
    char ch=originalStr.charAt(i);
    if (Character.isWhitespace(ch)) {
      return true;
    }
  }
  return false;
}
