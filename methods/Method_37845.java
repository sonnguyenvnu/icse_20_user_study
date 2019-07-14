/** 
 * Indicates whether the given character is in the <i>pchar</i> set.
 * @see <a href="http://www.ietf.org/rfc/rfc3986.txt">RFC 3986, appendix A</a>
 */
public static boolean isPchar(final char c){
  return isUnreserved(c) || isSubDelimiter(c) || c == ':' || c == '@';
}
