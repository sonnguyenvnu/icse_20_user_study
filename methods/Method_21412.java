private DruidConnectionHolder pollLast(long nanos) throws InterruptedException, SQLException {
  long estimate=nanos;
  for (; ; ) {
    if (poolingCount == 0) {
      emptySignal();
      if (failFast && isFailContinuous()) {
        throw new DataSourceNotAvailableException(createError);
      }
      if (estimate <= 0) {
        waitNanosLocal.set(nanos - estimate);
        return null;
      }
      notEmptyWaitThreadCount++;
      if (notEmptyWaitThreadCount > notEmptyWaitThreadPeak) {
        notEmptyWaitThreadPeak=notEmptyWaitThreadCount;
      }
      try {
        long startEstimate=estimate;
        estimate=notEmpty.awaitNanos(estimate);
        notEmptyWaitCount++;
        notEmptyWaitNanos+=(startEstimate - estimate);
        if (!enable) {
          connectErrorCountUpdater.incrementAndGet(this);
          throw new DataSourceDisableException();
        }
      }
 catch (      InterruptedException ie) {
        notEmpty.signal();
        notEmptySignalCount++;
        throw ie;
      }
 finally {
        notEmptyWaitThreadCount--;
      }
      if (poolingCount == 0) {
        if (estimate > 0) {
          continue;
        }
        waitNanosLocal.set(nanos - estimate);
        return null;
      }
    }
    decrementPoolingCount();
    DruidConnectionHolder last=connections[poolingCount];
    connections[poolingCount]=null;
    long waitNanos=nanos - estimate;
    last.setLastNotEmptyWaitNanos(waitNanos);
    return last;
  }
}
