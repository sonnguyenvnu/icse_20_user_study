public static byte[] getBytesFromFile(File file) throws IOException {
  InputStream is=null;
  try {
    is=new FileInputStream(file);
    long length=file.length();
    if (length > Integer.MAX_VALUE) {
      throw new IOException("file too large: " + file);
    }
    byte[] bytes=new byte[(int)length];
    int offset=0;
    int numRead=0;
    while (offset < bytes.length && (numRead=is.read(bytes,offset,bytes.length - offset)) >= 0) {
      offset+=numRead;
    }
    if (offset < bytes.length) {
      throw new IOException("Failed to read whole file " + file);
    }
    return bytes;
  }
  finally {
    if (is != null) {
      is.close();
    }
  }
}
