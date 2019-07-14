@Override public Object read(Type type,Class<?> contextClass,HttpInputMessage inputMessage) throws IOException {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  InputStream in=inputMessage.getBody();
  byte[] buf=new byte[1024];
  for (; ; ) {
    int len=in.read(buf);
    if (len == -1) {
      break;
    }
    if (len > 0) {
      baos.write(buf,0,len);
    }
  }
  byte[] bytes=baos.toByteArray();
  return readByBytes(type,bytes);
}
