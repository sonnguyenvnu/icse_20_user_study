/** 
 * Check to make sure that this stream has not been closed
 */
private void ensureOpen() throws IOException {
  if (state == States.CLOSED) {
    throw new IOException("Stream closed");
  }
}
