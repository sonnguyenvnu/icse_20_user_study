/** 
 * Ensure that the current stream is valid, that is underlying closeable reference is not null and is valid
 * @throws InvalidStreamException if the stream is invalid
 */
private void ensureValid(){
  if (!CloseableReference.isValid(mBufRef)) {
    throw new InvalidStreamException();
  }
}
