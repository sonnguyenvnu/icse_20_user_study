/** 
 * Schedule the next frame to be rendered after the given delay.
 * @param targetAnimationTimeMs the time in ms to update the frame
 */
private void scheduleNextFrame(long targetAnimationTimeMs){
  mExpectedRenderTimeMs=mStartTimeMs + targetAnimationTimeMs;
  scheduleSelf(mInvalidateRunnable,mExpectedRenderTimeMs);
}
