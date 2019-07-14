protected static IntBuffer allocateIntBuffer(int size){
  if (USE_DIRECT_BUFFERS) {
    return allocateDirectIntBuffer(size);
  }
 else {
    return IntBuffer.allocate(size);
  }
}
