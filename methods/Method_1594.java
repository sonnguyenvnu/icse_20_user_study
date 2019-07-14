/** 
 * Gets a PooledByteBuffer from the current contents. If the stream has already been closed, then an InvalidStreamException is thrown.
 * @return a PooledByteBuffer instance for the contents of the stream
 * @throws InvalidStreamException if the stream is invalid
 */
@Override public MemoryPooledByteBuffer toByteBuffer(){
  ensureValid();
  return new MemoryPooledByteBuffer(mBufRef,mCount);
}
