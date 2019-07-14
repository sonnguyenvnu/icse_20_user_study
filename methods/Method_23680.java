/** 
 * Consume the next character, and check that it matches a specified character.
 * @param c The character to match.
 * @return The character.
 * @throws JSONException if the character does not match.
 */
public char next(char c){
  char n=this.next();
  if (n != c) {
    throw new RuntimeException("Expected '" + c + "' and instead saw '" + n + "'");
  }
  return n;
}
