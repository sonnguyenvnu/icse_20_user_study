/** 
 * Reads the given input stream and returns its content as a byte array.
 * @param inputStream an input stream.
 * @param close true to close the input stream after reading.
 * @return the content of the given input stream.
 * @throws IOException if a problem occurs during reading.
 */
private static byte[] readStream(final InputStream inputStream,final boolean close) throws IOException {
  if (inputStream == null) {
    throw new IOException("Class not found");
  }
  try (ByteArrayOutputStream outputStream=new ByteArrayOutputStream()){
    byte[] data=new byte[INPUT_STREAM_DATA_CHUNK_SIZE];
    int bytesRead;
    while ((bytesRead=inputStream.read(data,0,data.length)) != -1) {
      outputStream.write(data,0,bytesRead);
    }
    outputStream.flush();
    return outputStream.toByteArray();
  }
  finally {
    if (close) {
      inputStream.close();
    }
  }
}
