@Override public MemoryPooledByteBufferOutputStream newOutputStream(){
  return new MemoryPooledByteBufferOutputStream(mPool);
}
