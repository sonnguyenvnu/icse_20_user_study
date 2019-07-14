public int getPoolingPeak(){
  lock.lock();
  try {
    return poolingPeak;
  }
  finally {
    lock.unlock();
  }
}
