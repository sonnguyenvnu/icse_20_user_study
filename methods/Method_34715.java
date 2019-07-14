/** 
 * Rolling count of number of threads executed during rolling statistical window. <p> The rolling window is defined by  {@link HystrixThreadPoolProperties#metricsRollingStatisticalWindowInMilliseconds()}.
 * @return rolling count of threads executed
 */
public long getRollingCountThreadsExecuted(){
  return rollingCounterStream.getLatestCount(HystrixEventType.ThreadPool.EXECUTED);
}
