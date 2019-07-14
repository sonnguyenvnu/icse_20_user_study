public byte[] readFully(int len) throws IOException {
  byte[] bytes=new byte[len];
  readFully(bytes,0,len);
  return bytes;
}
