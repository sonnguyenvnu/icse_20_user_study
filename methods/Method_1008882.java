/** 
 * Read 1 byte.
 * @return One byte
 * @throws IOException If EOF is reached
 */
public byte read() throws IOException {
  if (current >= fsize) {
    throw new java.io.EOFException("Reached EOF, file size=" + fsize);
  }
  final byte ret=file[current++];
  return ret;
}
