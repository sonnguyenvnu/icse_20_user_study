private ByteArrayResource convertToByteArrayResource(InputStream inputStream){
  try {
    int nRead;
    byte[] data=new byte[2048];
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    while ((nRead=inputStream.read(data,0,data.length)) != -1) {
      buffer.write(data,0,nRead);
    }
    buffer.flush();
    return new ByteArrayResource(buffer.toByteArray());
  }
 catch (  Throwable t) {
    throw new RuntimeException(t);
  }
}
