/** 
 * Indicates whether the given character is in the <i>sub-delims</i> set.
 * @see <a href="http://www.ietf.org/rfc/rfc3986.txt">RFC 3986, appendix A</a>
 */
public static boolean isSubDelimiter(final int c){
switch (c) {
case '!':
case '$':
case '&':
case '\'':
case '(':
case ')':
case '*':
case '+':
case ',':
case ';':
case '=':
    return true;
default :
  return false;
}
}
