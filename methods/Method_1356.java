/** 
 * Thread-safe call to reset the disk stats. If we know that the free space has changed recently (for example, if we have deleted files), use this method to reset the internal state and start tracking disk stats afresh, resetting the internal timer for updating stats.
 */
public void resetStats(){
  if (lock.tryLock()) {
    try {
      ensureInitialized();
      updateStats();
    }
  finally {
      lock.unlock();
    }
  }
}
