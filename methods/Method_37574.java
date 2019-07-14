/** 
 * Reads all available bytes from  {@link InputStream} as a byte array.Uses  {@link InputStream#available()} to determine the size of input stream.This is the fastest method for reading  {@link InputStream} to byte array, butdepends on  {@link InputStream} implementation of {@link InputStream#available()}.
 * @param input {@link InputStream} to read.
 * @return byte[]
 * @throws IOException if total read is less than {@link InputStream#available()};
 */
public static byte[] readAvailableBytes(final InputStream input) throws IOException {
  int numToRead=input.available();
  byte[] buffer=new byte[numToRead];
  int totalRead=ZERO;
  int read;
  while ((totalRead < numToRead) && (read=input.read(buffer,totalRead,numToRead - totalRead)) >= ZERO) {
    totalRead=totalRead + read;
  }
  if (totalRead < numToRead) {
    throw new IOException("Failed to completely read InputStream");
  }
  return buffer;
}
