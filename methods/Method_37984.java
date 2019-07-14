/** 
 * Returns if string starts with given character.
 */
public static boolean startsWithChar(final String s,final char c){
  if (s.length() == 0) {
    return false;
  }
  return s.charAt(0) == c;
}
