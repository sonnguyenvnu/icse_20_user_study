/** 
 * Check whether the given  {@code CharSequence} contains actual <em>text</em>.<p>More specifically, this method returns  {@code true} if the{@code CharSequence} is not {@code null}, its length is greater than 0, and it contains at least one non-whitespace character. <p><pre class="code"> StringUtils.hasText(null) = false StringUtils.hasText("") = false StringUtils.hasText(" ") = false StringUtils.hasText("12345") = true StringUtils.hasText(" 12345 ") = true </pre>
 * @param str the {@code CharSequence} to check (may be {@code null})
 * @return {@code true} if the {@code CharSequence} is not {@code null}, its length is greater than 0, and it does not contain whitespace only
 * @see Character#isWhitespace
 */
public static boolean hasText(CharSequence str){
  return (hasLength(str) && containsText(str));
}
