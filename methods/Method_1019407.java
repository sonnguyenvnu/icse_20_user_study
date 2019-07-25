private static byte[] read(InputStream input) throws IOException {
  ByteArrayOutputStream buffer=new ByteArrayOutputStream();
  int n;
  byte[] temp=new byte[4096];
  while ((n=input.read(temp,0,temp.length)) != -1) {
    buffer.write(temp,0,n);
  }
  return buffer.toByteArray();
}
