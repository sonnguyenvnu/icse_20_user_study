@Override public MemoryPooledByteBufferOutputStream newOutputStream(int initialCapacity){
  return new MemoryPooledByteBufferOutputStream(mPool,initialCapacity);
}
