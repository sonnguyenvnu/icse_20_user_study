/** 
 * Check whether the given  {@code CharSequence} contains any whitespace characters.
 * @param str the {@code CharSequence} to check (may be {@code null})
 * @return {@code true} if the {@code CharSequence} is not empty andcontains at least 1 whitespace character
 * @see Character#isWhitespace
 */
public static boolean containsWhitespace(CharSequence str){
  if (!hasLength(str)) {
    return false;
  }
  int strLen=str.length();
  for (int i=0; i < strLen; i++) {
    if (Character.isWhitespace(str.charAt(i))) {
      return true;
    }
  }
  return false;
}
