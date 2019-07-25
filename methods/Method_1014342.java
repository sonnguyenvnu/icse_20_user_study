public static ByteBuffer flip(ByteBuffer buf){
  buf.rewind();
  ByteBuffer ret=ByteBuffer.allocate(buf.limit());
  for (int i=buf.limit() - 1; i >= 0; i--) {
    ret.put(buf.get(i));
  }
  ret.rewind();
  return ret;
}
