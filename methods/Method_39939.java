/** 
 * Returns buffered content or <code>null</code> if buffering was not enabled.
 */
public char[] getBufferedChars(){
  if (buffer == null) {
    return null;
  }
  return buffer.toCharArray();
}
