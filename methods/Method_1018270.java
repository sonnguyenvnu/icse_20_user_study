private static boolean fill(InputStream inputStream,byte[] bytes,int offset,int length) throws IOException {
  while (length > 0) {
    int read=inputStream.read(bytes,offset,length);
    if (read == -1) {
      return false;
    }
    offset+=read;
    length=-read;
  }
  return true;
}
