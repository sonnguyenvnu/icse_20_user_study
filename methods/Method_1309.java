/** 
 * Reads a file of the given expected size from the given input stream, if it will fit into a byte array. This method handles the case where the file size changes between when the size is read and when the contents are read from the stream.
 */
static byte[] readFile(InputStream in,long expectedSize) throws IOException {
  if (expectedSize > Integer.MAX_VALUE) {
    throw new OutOfMemoryError("file is too large to fit in a byte array: " + expectedSize + " bytes");
  }
  return expectedSize == 0 ? ByteStreams.toByteArray(in) : ByteStreams.toByteArray(in,(int)expectedSize);
}
