/** 
 * If the string starts and ends with start and end char, remove them, otherwise return the string as it was passed in.
 */
private static String removeAnyBounding(final char s,final char e,final String str){
  if (str == null || str.length() < 2) {
    return str;
  }
  if (str.startsWith(String.valueOf(s)) && str.endsWith(String.valueOf(e))) {
    return str.substring(1,str.length() - 1);
  }
  return str;
}
