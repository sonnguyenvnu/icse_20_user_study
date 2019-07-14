/** 
 * Checks whether this character is a linebreak character.
 * @param ch The character
 * @return {@code true} if it is, {@code false} if not.
 */
private static boolean isLineBreakCharacter(char ch){
  return '\n' == ch || '\r' == ch;
}
