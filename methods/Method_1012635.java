/** 
 * @return The number of executable paths registered.
 */
public int size(){
  lock.readLock().lock();
  try {
    return executablesInfo.size();
  }
  finally {
    lock.readLock().unlock();
  }
}
