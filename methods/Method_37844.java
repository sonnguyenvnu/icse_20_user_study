/** 
 * Indicates whether the given character is in the <i>unreserved</i> set.
 * @see <a href="http://www.ietf.org/rfc/rfc3986.txt">RFC 3986, appendix A</a>
 */
public static boolean isUnreserved(final char c){
  return isAlpha(c) || isDigit(c) || c == '-' || c == '.' || c == '_' || c == '~';
}
