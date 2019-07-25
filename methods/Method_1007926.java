/** 
 * Performs clean-up.
 */
public void destroy(){
  if (mSplashImageTransferTask != null) {
    mSplashImageTransferTask.cancel();
  }
}
