@Override public synchronized int read(final int memoryOffset,final byte[] byteArray,final int byteArrayOffset,final int count){
  Preconditions.checkNotNull(byteArray);
  Preconditions.checkState(!isClosed());
  final int actualCount=MemoryChunkUtil.adjustByteCount(memoryOffset,count,mSize);
  MemoryChunkUtil.checkBounds(memoryOffset,byteArray.length,byteArrayOffset,actualCount,mSize);
  mBuffer.position(memoryOffset);
  mBuffer.get(byteArray,byteArrayOffset,actualCount);
  return actualCount;
}
