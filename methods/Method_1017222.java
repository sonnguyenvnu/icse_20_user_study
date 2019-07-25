public ByteBuffer serialize(){
  final ByteBuffer buffer=ByteBuffer.allocate(buffers.size() * 5 + size);
  for (  final ByteBuffer b : buffers) {
    buffer.putShort((short)b.limit());
    buffer.put(b);
    buffer.put(EQ);
  }
  buffer.flip();
  return buffer;
}
