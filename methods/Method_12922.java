/** 
 * Check that the given  {@code CharSequence} is neither {@code null} norof length 0. <p>Note: this method returns  {@code true} for a {@code CharSequence}that purely consists of whitespace. <p><pre class="code"> StringUtils.hasLength(null) = false StringUtils.hasLength("") = false StringUtils.hasLength(" ") = true StringUtils.hasLength("Hello") = true </pre>
 * @param str the {@code CharSequence} to check (may be {@code null})
 * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
 * @see #hasText(String)
 */
public static boolean hasLength(CharSequence str){
  return (str != null && str.length() > 0);
}
