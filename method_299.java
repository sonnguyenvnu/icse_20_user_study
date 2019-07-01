/** 
 * Deallocates the object and sets it  {@link PooledObjectState#IDLE IDLE}if it is currently  {@link PooledObjectState#ALLOCATED ALLOCATED}.
 * @return {@code true} if the state was {@link PooledObjectState#ALLOCATED ALLOCATED}
 */
@Override public synchronized boolean _XXXXX_(){
  if (state == PooledObjectState.ALLOCATED || state == PooledObjectState.RETURNING) {
    state=PooledObjectState.IDLE;
    lastReturnTime=System.currentTimeMillis();
    borrowedBy.clear();
    return true;
  }
  return false;
}