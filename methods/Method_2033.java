/** 
 * Called whenever image request is cancelled, that is whenever image view is reused without setting final image first
 */
public void reportCancellation(long waitTime){
  mSumOfWaitTime+=waitTime;
  mCancelledRequests++;
}
