/** 
 * Indicates whether the given character is in the <i>reserved</i> set.
 * @see <a href="http://www.ietf.org/rfc/rfc3986.txt">RFC 3986, appendix A</a>
 */
public static boolean isReserved(final char c){
  return isGenericDelimiter(c) || isSubDelimiter(c);
}
