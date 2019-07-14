DruidConnectionHolder takeLast() throws InterruptedException, SQLException {
  try {
    while (poolingCount == 0) {
      emptySignal();
      if (failFast && isFailContinuous()) {
        throw new DataSourceNotAvailableException(createError);
      }
      notEmptyWaitThreadCount++;
      if (notEmptyWaitThreadCount > notEmptyWaitThreadPeak) {
        notEmptyWaitThreadPeak=notEmptyWaitThreadCount;
      }
      try {
        notEmpty.await();
      }
  finally {
        notEmptyWaitThreadCount--;
      }
      notEmptyWaitCount++;
      if (!enable) {
        connectErrorCountUpdater.incrementAndGet(this);
        throw new DataSourceDisableException();
      }
    }
  }
 catch (  InterruptedException ie) {
    notEmpty.signal();
    notEmptySignalCount++;
    throw ie;
  }
  decrementPoolingCount();
  DruidConnectionHolder last=connections[poolingCount];
  connections[poolingCount]=null;
  return last;
}
