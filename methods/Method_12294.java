/** 
 * Stops the animation. Does nothing if GIF is not animated. This method is thread-safe.
 */
@Override public void stop(){
synchronized (this) {
    if (!mIsRunning) {
      return;
    }
    mIsRunning=false;
  }
  cancelPendingRenderTask();
  mNativeInfoHandle.saveRemainder();
}
