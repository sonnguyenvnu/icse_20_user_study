/** 
 * Tells whether this stream is ready to be read.
 * @return True if the next read() is guaranteed not to block for input
 * @throws IOException If the stream is closed
 */
@Override public boolean ready() throws IOException {
  ensureOpen();
  return true;
}
