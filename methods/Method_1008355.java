/** 
 * Tells whether this stream is ready to be read.  Character-array readers are always ready to be read.
 * @throws IOException If an I/O error occurs
 */
@Override public boolean ready() throws IOException {
  ensureOpen();
  return (count - pos) > 0;
}
