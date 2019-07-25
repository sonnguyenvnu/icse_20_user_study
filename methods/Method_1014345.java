@Override public Long value(ByteBuffer bytes){
  return bytes.getInt() & 0xFFFFFFFFL;
}
