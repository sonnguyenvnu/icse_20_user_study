protected static FloatBuffer allocateFloatBuffer(int size){
  if (USE_DIRECT_BUFFERS) {
    return allocateDirectFloatBuffer(size);
  }
 else {
    return FloatBuffer.allocate(size);
  }
}
