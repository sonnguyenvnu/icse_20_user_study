/** 
 * Checks whether this characters matches any of these characters.
 * @param c     The char to check.
 * @param chars The chars that should match.
 * @return {@code true} if it does, {@code false if not}.
 */
public static boolean isCharAnyOf(char c,String chars){
  for (int i=0; i < chars.length(); i++) {
    if (chars.charAt(i) == c) {
      return true;
    }
  }
  return false;
}
