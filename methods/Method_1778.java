/** 
 * Reads up to maxHeaderLength bytes from is InputStream. If mark is supported by is, it is used to restore content of the stream after appropriate amount of data is read. Read bytes are stored in imageHeaderBytes, which should be capable of storing maxHeaderLength bytes.
 * @param maxHeaderLength the maximum header length
 * @param is
 * @param imageHeaderBytes
 * @return number of bytes read from is
 * @throws IOException
 */
private static int readHeaderFromStream(int maxHeaderLength,final InputStream is,final byte[] imageHeaderBytes) throws IOException {
  Preconditions.checkNotNull(is);
  Preconditions.checkNotNull(imageHeaderBytes);
  Preconditions.checkArgument(imageHeaderBytes.length >= maxHeaderLength);
  if (is.markSupported()) {
    try {
      is.mark(maxHeaderLength);
      return ByteStreams.read(is,imageHeaderBytes,0,maxHeaderLength);
    }
  finally {
      is.reset();
    }
  }
 else {
    return ByteStreams.read(is,imageHeaderBytes,0,maxHeaderLength);
  }
}
