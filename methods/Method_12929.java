/** 
 * Trim <i>all</i> whitespace from the given  {@code String}: leading, trailing, and in between characters.
 * @param str the {@code String} to check
 * @return the trimmed {@code String}
 * @see Character#isWhitespace
 */
public static String trimAllWhitespace(String str){
  if (!hasLength(str)) {
    return str;
  }
  int len=str.length();
  StringBuilder sb=new StringBuilder(str.length());
  for (int i=0; i < len; i++) {
    char c=str.charAt(i);
    if (!Character.isWhitespace(c)) {
      sb.append(c);
    }
  }
  return sb.toString();
}
