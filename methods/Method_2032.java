/** 
 * Called whenever image request fails, that is whenever failure drawable is set.
 */
public void reportFailure(long waitTime){
  mSumOfWaitTime+=waitTime;
  mFailedRequests++;
}
