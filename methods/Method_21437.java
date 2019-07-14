public boolean isFull(){
  lock.lock();
  try {
    return this.poolingCount + this.activeCount >= this.maxActive;
  }
  finally {
    lock.unlock();
  }
}
