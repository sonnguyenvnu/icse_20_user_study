@Override public String deserialize(ByteBuffer buffer){
  final byte flag=buffer.get();
  if (flag == IS_NULL) {
    return null;
  }
  if (flag == IS_EMPTY) {
    return EMPTY;
  }
  final byte[] bytes=new byte[buffer.remaining()];
  buffer.get(bytes);
  return new String(bytes,UTF8);
}
