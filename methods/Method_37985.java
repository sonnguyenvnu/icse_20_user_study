/** 
 * Returns if string ends with provided character.
 */
public static boolean endsWithChar(final String s,final char c){
  if (s.length() == 0) {
    return false;
  }
  return s.charAt(s.length() - 1) == c;
}
