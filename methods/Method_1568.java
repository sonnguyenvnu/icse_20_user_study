@Override public synchronized int write(final int memoryOffset,final byte[] byteArray,final int byteArrayOffset,final int count){
  Preconditions.checkNotNull(byteArray);
  Preconditions.checkState(!isClosed());
  final int actualCount=MemoryChunkUtil.adjustByteCount(memoryOffset,count,mSize);
  MemoryChunkUtil.checkBounds(memoryOffset,byteArray.length,byteArrayOffset,actualCount,mSize);
  mBuffer.position(memoryOffset);
  mBuffer.put(byteArray,byteArrayOffset,actualCount);
  return actualCount;
}
