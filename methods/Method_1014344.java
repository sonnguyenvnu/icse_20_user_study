@Override public Integer value(ByteBuffer bytes){
  return bytes.getShort() & 0xFFFF;
}
