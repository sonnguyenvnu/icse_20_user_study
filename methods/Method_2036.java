/** 
 * @return number of completed requests, either by seting final image, failure or cancellation
 */
public long getCompletedRequests(){
  return mSuccessfulRequests + mCancelledRequests + mFailedRequests;
}
