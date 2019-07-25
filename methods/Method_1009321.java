public void fill(byte[] bytes,int offset,int length) throws IOException {
  int remaining=length;
  while (remaining != 0) {
    int read=read(bytes,offset + length - remaining,remaining);
    if (read == -1) {
      throw new EOFException();
    }
    remaining-=read;
  }
}
