@Override public Integer value(ByteBuffer bytes){
  return (int)(bytes.get() & 0xFF);
}
