@Override public ByteBuffer serialize(Long value){
  final ByteBuffer buffer=ByteBuffer.allocate(8).putLong(value);
  buffer.flip();
  return buffer;
}
