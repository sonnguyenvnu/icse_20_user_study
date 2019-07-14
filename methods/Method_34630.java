/** 
 * Best-effort attempt to remove an argument from a batch.  This may get invoked when a cancellation occurs somewhere downstream. This method finds the argument in the batch, and removes it.
 * @param arg argument to remove from batch
 */
void remove(RequestArgumentType arg){
  if (batchStarted.get()) {
    return;
  }
  if (batchLock.readLock().tryLock()) {
    try {
      if (batchStarted.get()) {
        return;
      }
      argumentMap.remove(arg);
    }
  finally {
      batchLock.readLock().unlock();
    }
  }
}
