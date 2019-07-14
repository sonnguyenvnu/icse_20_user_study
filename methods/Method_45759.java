/** 
 * <p>Gets the substring before the first occurrence of a separator. The separator is not returned.</p> <p>A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty string. A <code>null</code> separator will return the input string.</p> <p>If nothing is found, the string input is returned.</p> <pre> StringUtils.substringBefore(null, *)      = null StringUtils.substringBefore("", *)        = "" StringUtils.substringBefore("abc", "a")   = "" StringUtils.substringBefore("abcba", "b") = "a" StringUtils.substringBefore("abc", "c")   = "ab" StringUtils.substringBefore("abc", "d")   = "abc" StringUtils.substringBefore("abc", "")    = "" StringUtils.substringBefore("abc", null)  = "abc" </pre>
 * @param str  the String to get a substring from, may be null
 * @param separator  the String to search for, may be null
 * @return the substring before the first occurrence of the separator,<code>null</code> if null String input
 * @since 2.0
 */
public static String substringBefore(String str,String separator){
  if (isEmpty(str) || separator == null) {
    return str;
  }
  if (separator.length() == 0) {
    return EMPTY;
  }
  int pos=str.indexOf(separator);
  if (pos == -1) {
    return str;
  }
  return str.substring(0,pos);
}
