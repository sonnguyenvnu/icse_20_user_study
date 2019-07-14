/** 
 * Reallocate the local buffer to hold the new length specified. Also copy over existing data to this new buffer
 * @param newLength new length of buffer
 * @throws InvalidStreamException if the stream is invalid
 * @throws BasePool.SizeTooLargeException if the allocation from the pool fails
 */
@VisibleForTesting void realloc(int newLength){
  ensureValid();
  if (newLength <= mBufRef.get().getSize()) {
    return;
  }
  MemoryChunk newbuf=mPool.get(newLength);
  mBufRef.get().copy(0,newbuf,0,mCount);
  mBufRef.close();
  mBufRef=CloseableReference.of(newbuf,mPool);
}
