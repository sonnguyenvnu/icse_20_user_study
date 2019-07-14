/** 
 * Returns buffered servlet output content as byte array. Returns <code>null</code> if writer is not used.
 */
public byte[] toByteArray(){
  if (bufferOutputStream != null) {
    return bufferOutputStream.getByteArrayStream().toByteArray();
  }
  return null;
}
