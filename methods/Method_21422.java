public int getWaitThreadCount(){
  lock.lock();
  try {
    return lock.getWaitQueueLength(notEmpty);
  }
  finally {
    lock.unlock();
  }
}
