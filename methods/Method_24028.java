protected static ShortBuffer allocateDirectShortBuffer(int size){
  int bytes=PApplet.max(MIN_DIRECT_BUFFER_SIZE,size) * SIZEOF_SHORT;
  return ByteBuffer.allocateDirect(bytes).order(ByteOrder.nativeOrder()).asShortBuffer();
}
