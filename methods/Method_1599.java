@Override protected NativeMemoryChunk alloc(int bucketedSize){
  return new NativeMemoryChunk(bucketedSize);
}
