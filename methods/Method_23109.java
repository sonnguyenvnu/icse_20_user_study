public void writeShorts(short[] s,int off,int len) throws IOException {
  if (off < 0 || len < 0 || off + len > s.length || off + len < 0) {
    throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > s.length!");
  }
  byte[] b=new byte[len * 2];
  int boff=0;
  for (int i=0; i < len; i++) {
    short v=s[off + i];
    b[boff++]=(byte)(v >>> 8);
    b[boff++]=(byte)(v >>> 0);
  }
  write(b,0,len * 2);
}
