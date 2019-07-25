/** 
 * <p>Compares two CharSequences, returning  {@code true} if they are equal.</p><p> {@code null}s are handled without exceptions. Two  {@code null}references are considered to be equal. The comparison is case sensitive.</p> <pre> StringUtils.equals(null, null)   = true StringUtils.equals(null, "abc")  = false StringUtils.equals("abc", null)  = false StringUtils.equals("abc", "abc") = true StringUtils.equals("abc", "ABC") = false </pre>
 * @see java.lang.String#equals(Object)
 * @param cs1  the first CharSequence, may be null
 * @param cs2  the second CharSequence, may be null
 * @return {@code true} if the CharSequences are equal, case sensitive, orboth  {@code null}
 * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
 */
public static boolean equals(CharSequence cs1,CharSequence cs2){
  return cs1 == null ? cs2 == null : cs1.equals(cs2);
}
