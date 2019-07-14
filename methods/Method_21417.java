private boolean put(DruidConnectionHolder holder){
  lock.lock();
  try {
    if (poolingCount >= maxActive) {
      return false;
    }
    connections[poolingCount]=holder;
    incrementPoolingCount();
    if (poolingCount > poolingPeak) {
      poolingPeak=poolingCount;
      poolingPeakTime=System.currentTimeMillis();
    }
    notEmpty.signal();
    notEmptySignalCount++;
    if (createScheduler != null) {
      createTaskCount--;
      if (poolingCount + createTaskCount < notEmptyWaitThreadCount && activeCount + poolingCount + createTaskCount < maxActive) {
        emptySignal();
      }
    }
  }
  finally {
    lock.unlock();
  }
  return true;
}
