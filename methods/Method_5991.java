/** 
 * Converts the entirety of an  {@link InputStream} to a byte array.
 * @param inputStream the {@link InputStream} to be read. The input stream is not closed by thismethod.
 * @return a byte array containing all of the inputStream's bytes.
 * @throws IOException if an error occurs reading from the stream.
 */
public static byte[] toByteArray(InputStream inputStream) throws IOException {
  byte[] buffer=new byte[1024 * 4];
  ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
  int bytesRead;
  while ((bytesRead=inputStream.read(buffer)) != -1) {
    outputStream.write(buffer,0,bytesRead);
  }
  return outputStream.toByteArray();
}
