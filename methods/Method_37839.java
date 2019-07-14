/** 
 * Indicates whether the given character is in the  {@code DIGIT} set.
 * @see <a href="http://www.ietf.org/rfc/rfc3986.txt">RFC 3986, appendix A</a>
 */
public static boolean isDigit(final char c){
  return c >= '0' && c <= '9';
}
