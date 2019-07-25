public ByteBuffer append(final int i){
  write((byte)(i & 0xFF));
  return this;
}
