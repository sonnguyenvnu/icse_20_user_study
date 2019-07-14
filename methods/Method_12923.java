/** 
 * Check that the given  {@code String} is neither {@code null} nor of length 0.<p>Note: this method returns  {@code true} for a {@code String} thatpurely consists of whitespace.
 * @param str the {@code String} to check (may be {@code null})
 * @return {@code true} if the {@code String} is not {@code null} and has length
 * @see #hasLength(CharSequence)
 * @see #hasText(String)
 */
public static boolean hasLength(String str){
  return (str != null && !str.isEmpty());
}
