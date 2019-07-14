public static ByteArrayOtherStream createByteArrayOtherStream(InputStream is) throws IOException {
  if (is == null)   return null;
  int size=is.available();
  size=Math.max(102400,size);
  int bufferSize=Math.min(1048576,size);
  byte[] bytes=new byte[bufferSize];
  if (IOUtil.readBytesFromOtherInputStream(is,bytes) == 0) {
    throw new IOException("??????????InputStream????????");
  }
  return new ByteArrayOtherStream(bytes,bufferSize,is);
}
