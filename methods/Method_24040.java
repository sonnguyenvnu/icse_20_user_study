protected static FloatBuffer allocateDirectFloatBuffer(int size){
  int bytes=PApplet.max(MIN_DIRECT_BUFFER_SIZE,size) * SIZEOF_FLOAT;
  return ByteBuffer.allocateDirect(bytes).order(ByteOrder.nativeOrder()).asFloatBuffer();
}
