private byte[] CopySdcardbytes(File file) throws IOException {
  FileInputStream in=new FileInputStream(file);
  ByteArrayOutputStream out=new ByteArrayOutputStream(1024);
  byte[] temp=new byte[1024];
  int size=0;
  while ((size=in.read(temp)) != -1) {
    out.write(temp,0,size);
  }
  in.close();
  byte[] bytes=out.toByteArray();
  return bytes;
}
