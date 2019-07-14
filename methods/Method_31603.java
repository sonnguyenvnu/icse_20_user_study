/** 
 * Peek ahead in the stream to see if the next characters match this string exactly.
 * @param str The string to match.
 * @return {@code true} if they do, {@code false} if not.
 */
public boolean peek(String str) throws IOException {
  return str.equals(peek(str.length()));
}
