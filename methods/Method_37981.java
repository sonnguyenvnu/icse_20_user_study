/** 
 * Splits a string in several parts (tokens) that are separated by delimiter characters. Delimiter may contains any number of character and it is always surrounded by two strings.
 * @param src    source to examine
 * @param d      string with delimiter characters
 * @return array of tokens
 */
public static String[] splitc(final String src,final String d){
  if ((d.length() == 0) || (src.length() == 0)) {
    return new String[]{src};
  }
  return splitc(src,d.toCharArray());
}
