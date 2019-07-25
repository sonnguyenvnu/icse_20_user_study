static void encode(ByteBuffer buf,int n){
  int k=0;
  while (n < 0 || n > 0x7f) {
    byte b=(byte)(0x80 | (0x7f & n));
    buf.put(b);
    n=n >>> 7;
    k++;
    if (k >= 6) {
      throw new IllegalStateException("Size is implausibly large");
    }
  }
  buf.put((byte)n);
}
