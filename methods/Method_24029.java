protected static ShortBuffer allocateShortBuffer(int size){
  if (USE_DIRECT_BUFFERS) {
    return allocateDirectShortBuffer(size);
  }
 else {
    return ShortBuffer.allocate(size);
  }
}
