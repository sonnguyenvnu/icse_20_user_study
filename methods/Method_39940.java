/** 
 * Returns buffered bytes or <code>null</code> if buffering was not enabled.
 */
public byte[] getBufferedBytes(){
  if (buffer == null) {
    return null;
  }
  return buffer.toByteArray();
}
