public static byte[] get(InputStream inputStream,long length) throws IOException {
  if (length == 0) {
    return EMPTY_BYTES;
  }
  byte[] bytes=new byte[(int)length];
  if (!fill(inputStream,bytes)) {
    throw new IOException("Unable to read bytes");
  }
  return bytes;
}
