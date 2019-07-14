private ByteBuffer createReplacementByteBuffer(int requiredCapacity){
  if (bufferReplacementMode == BUFFER_REPLACEMENT_MODE_NORMAL) {
    return ByteBuffer.allocate(requiredCapacity);
  }
 else   if (bufferReplacementMode == BUFFER_REPLACEMENT_MODE_DIRECT) {
    return ByteBuffer.allocateDirect(requiredCapacity);
  }
 else {
    int currentCapacity=data == null ? 0 : data.capacity();
    throw new IllegalStateException("Buffer too small (" + currentCapacity + " < " + requiredCapacity + ")");
  }
}
