private byte[] getBytes(InputStream is) throws Exception {
  ByteArrayOutputStream bos=new ByteArrayOutputStream();
  byte[] buffer=new byte[1024];
  int len=0;
  while ((len=is.read(buffer)) != -1) {
    bos.write(buffer,0,len);
  }
  is.close();
  bos.flush();
  byte[] result=bos.toByteArray();
  System.out.println(new String(result));
  return result;
}
