/** 
 * Trim the trailing linebreak (if any) from this string.
 * @param str The string.
 * @return The string without trailing linebreak.
 */
public static String trimLineBreak(String str){
  if (!hasLength(str)) {
    return str;
  }
  StringBuilder buf=new StringBuilder(str);
  while (buf.length() > 0 && isLineBreakCharacter(buf.charAt(buf.length() - 1))) {
    buf.deleteCharAt(buf.length() - 1);
  }
  return buf.toString();
}
