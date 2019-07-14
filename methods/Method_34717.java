/** 
 * Rolling count of number of threads rejected during rolling statistical window. <p> The rolling window is defined by  {@link HystrixThreadPoolProperties#metricsRollingStatisticalWindowInMilliseconds()}.
 * @return rolling count of threads rejected
 */
public long getRollingCountThreadsRejected(){
  return rollingCounterStream.getLatestCount(HystrixEventType.ThreadPool.REJECTED);
}
