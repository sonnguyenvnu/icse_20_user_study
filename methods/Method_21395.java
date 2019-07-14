public void setEnable(boolean enable){
  lock.lock();
  try {
    this.enable=enable;
    if (!enable) {
      notEmpty.signalAll();
      notEmptySignalCount++;
    }
  }
  finally {
    lock.unlock();
  }
}
