/** 
 * Called whenever image request finishes successfully, that is whenever final image is set.
 */
public void reportSuccess(long waitTime){
  mSumOfWaitTime+=waitTime;
  mSuccessfulRequests++;
}
