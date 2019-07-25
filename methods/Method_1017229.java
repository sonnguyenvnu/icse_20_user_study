@Override public ByteBuffer serialize(String value){
  if (value == null) {
    final ByteBuffer b=ByteBuffer.allocate(1).put((byte)IS_NULL);
    b.flip();
    return b;
  }
  if (value.isEmpty()) {
    final ByteBuffer b=ByteBuffer.allocate(1).put((byte)IS_EMPTY);
    b.flip();
    return b;
  }
  final byte[] bytes=value.getBytes(UTF8);
  final ByteBuffer buffer=ByteBuffer.allocate(1 + bytes.length);
  buffer.put((byte)IS_STRING);
  buffer.put(bytes);
  buffer.flip();
  return buffer;
}
