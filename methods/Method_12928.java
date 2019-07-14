/** 
 * Check whether the given  {@code String} contains any whitespace characters.
 * @param str the {@code String} to check (may be {@code null})
 * @return {@code true} if the {@code String} is not empty andcontains at least 1 whitespace character
 * @see #containsWhitespace(CharSequence)
 */
public static boolean containsWhitespace(String str){
  return containsWhitespace((CharSequence)str);
}
