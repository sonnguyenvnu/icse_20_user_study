/** 
 * Trim trailing whitespace from the given String.
 * @param str the String to check
 * @return the trimmed String
 * @see java.lang.Character#isWhitespace
 */
public static String trimTrailingWhitespace(String str){
  if (!hasLength(str)) {
    return str;
  }
  StringBuilder buf=new StringBuilder(str);
  while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
    buf.deleteCharAt(buf.length() - 1);
  }
  return buf.toString();
}
