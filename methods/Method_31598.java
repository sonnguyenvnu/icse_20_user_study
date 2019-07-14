/** 
 * Peek ahead in the stream to see if the next character matches this one.
 * @param c The character to match.
 * @return {@code true} if it does, {@code false} if not.
 */
public boolean peek(char c) throws IOException {
  int r=peek();
  return r != -1 && c == (char)r;
}
