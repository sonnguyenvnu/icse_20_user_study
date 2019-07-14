private MemoryChunkPool getMemoryChunkPool(@MemoryChunkType int memoryChunkType){
switch (memoryChunkType) {
case NATIVE_MEMORY:
    return getNativeMemoryChunkPool();
case BUFFER_MEMORY:
  return getBufferMemoryChunkPool();
default :
throw new IllegalArgumentException("Invalid MemoryChunkType");
}
}
