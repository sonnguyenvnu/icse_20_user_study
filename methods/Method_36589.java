/** 
 * Check whether the given  {@code String} contains actual <em>text</em>.<p>More specifically, this method returns  {@code true} if the{@code String} is not {@code null}, its length is greater than 0, and it contains at least one non-whitespace character.
 * @param str the {@code String} to check (may be {@code null})
 * @return {@code true} if the {@code String} is not {@code null}, its length is greater than 0, and it does not contain whitespace only
 * @see #hasText(CharSequence)
 */
public static boolean hasText(String str){
  return hasText((CharSequence)str);
}
