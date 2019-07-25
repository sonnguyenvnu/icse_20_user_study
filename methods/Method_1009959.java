static int decode(ByteBuffer buf){
  int v=buf.get();
  int z=0x7f & v;
  int shift=7;
  while ((v & 0x80) != 0) {
    if (shift > 28) {
      throw new IllegalStateException("Shift too large in decode");
    }
    v=buf.get();
    z+=(v & 0x7f) << shift;
    shift+=7;
  }
  return z;
}
