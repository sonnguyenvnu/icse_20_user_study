/** 
 * Returns whether the given character is a carriage return ('\r') or a line feed ('\n').
 * @param c The character.
 * @return Whether the given character is a linebreak.
 */
public static boolean isLinebreak(int c){
  return c == '\n' || c == '\r';
}
