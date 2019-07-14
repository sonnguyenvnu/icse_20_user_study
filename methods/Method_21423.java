public int getNotEmptyWaitThreadCount(){
  lock.lock();
  try {
    return notEmptyWaitThreadCount;
  }
  finally {
    lock.unlock();
  }
}
