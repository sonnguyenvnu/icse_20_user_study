/** 
 * Peek ahead in the stream to see if the next character is whitespace.
 * @return {@code true} if it is, {@code false} if not.
 */
public boolean peekWhitespace() throws IOException {
  int r=peek();
  return isWhitespace(r);
}
