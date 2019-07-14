/** 
 * Escapes the specified string.
 * @param str the specified string
 */
public static String escapeHTML(final String str){
  return Encode.forHtml(str);
}
