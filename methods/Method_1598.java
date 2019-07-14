/** 
 * This does actual copy. It should be called only when we hold locks on both this and other objects
 */
private void doCopy(final int offset,final MemoryChunk other,final int otherOffset,final int count){
  if (!(other instanceof NativeMemoryChunk)) {
    throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
  }
  Preconditions.checkState(!isClosed());
  Preconditions.checkState(!other.isClosed());
  MemoryChunkUtil.checkBounds(offset,other.getSize(),otherOffset,count,mSize);
  nativeMemcpy(other.getNativePtr() + otherOffset,mNativePtr + offset,count);
}
