/** 
 * @return difference between number of started requests and number of completed requests
 */
public long getOutstandingRequests(){
  return mStartedRequests - getCompletedRequests();
}
