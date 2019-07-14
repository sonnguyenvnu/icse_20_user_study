/** 
 * @return average wait time, that is sum of reported wait times divided by number of completedrequests
 */
public long getAverageWaitTime(){
  final long completedRequests=getCompletedRequests();
  return completedRequests > 0 ? mSumOfWaitTime / completedRequests : 0;
}
