/** 
 * Peek ahead in the stream to see if the next character is numeric.
 * @return {@code true} if it is, {@code false} if not.
 */
public boolean peekNumeric() throws IOException {
  int r=peek();
  return isNumeric(r);
}
