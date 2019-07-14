public int getNotEmptyWaitThreadPeak(){
  lock.lock();
  try {
    return notEmptyWaitThreadPeak;
  }
  finally {
    lock.unlock();
  }
}
