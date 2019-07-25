private final ByteBuffer remain(int length){
  ByteBuffer buffer;
  if (advanceBufferIfCurrentBufferHasNoRemaining() == false) {
    throw new BufferUnderflowException();
  }
  int remaining=_currentBuffer.remaining();
  if (remaining < length) {
    byte[] bytes=new byte[length];
    _currentBuffer.get(bytes,0,remaining);
    if (advanceBufferIfCurrentBufferHasNoRemaining() == false) {
      throw new BufferUnderflowException();
    }
    _currentBuffer.get(bytes,remaining,length - remaining);
    buffer=ByteBuffer.wrap(bytes);
    buffer.order(_order);
  }
 else {
    buffer=_currentBuffer;
  }
  return buffer;
}
