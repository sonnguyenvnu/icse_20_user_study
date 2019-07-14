byte[] readBytes(InputStream input,int len) throws IOException {
  int total=0;
  byte[] bytes=new byte[len];
  while (total < len) {
    int current=input.read(bytes,total,len - total);
    if (current > 0) {
      total+=current;
    }
 else {
      throw new EOFException();
    }
  }
  return bytes;
}
