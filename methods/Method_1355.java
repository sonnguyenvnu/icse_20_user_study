/** 
 * Thread-safe call to update disk stats. Update occurs if the thread is able to acquire the lock (i.e., no other thread is updating it at the same time), and it has been at least RESTAT_INTERVAL_MS since the last update. Assumes that initialization has been completed before this method is called.
 */
private void maybeUpdateStats(){
  if (lock.tryLock()) {
    try {
      if ((SystemClock.uptimeMillis() - mLastRestatTime) > RESTAT_INTERVAL_MS) {
        updateStats();
      }
    }
  finally {
      lock.unlock();
    }
  }
}
