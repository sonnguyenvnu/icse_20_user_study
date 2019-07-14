private void require(int size){
  if (buffer.capacity() - buffer.position() < size) {
    int newCapacity=buffer.position() + size + buffer.capacity();
    Preconditions.checkArgument(newCapacity <= MAX_BUFFER_CAPACITY,"Capacity exceeds max buffer capacity: %s",MAX_BUFFER_CAPACITY);
    ByteBuffer newBuffer=ByteBuffer.allocate(newCapacity);
    buffer.flip();
    newBuffer.put(buffer);
    buffer=newBuffer;
  }
}
