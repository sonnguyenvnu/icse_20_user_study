/** 
 * Decrements reference count for the shared reference. Returns value of mRefCount after decrementing
 */
private synchronized int decreaseRefCount(){
  ensureValid();
  Preconditions.checkArgument(mRefCount > 0);
  mRefCount--;
  return mRefCount;
}
