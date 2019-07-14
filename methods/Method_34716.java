/** 
 * Cumulative count of number of threads executed since the start of the application.
 * @return cumulative count of threads executed
 */
public long getCumulativeCountThreadsExecuted(){
  return cumulativeCounterStream.getLatestCount(HystrixEventType.ThreadPool.EXECUTED);
}
