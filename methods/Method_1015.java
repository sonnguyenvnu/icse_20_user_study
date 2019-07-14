/** 
 * Jump immediately to the given frame number. The animation will not be paused if it is running. If the animation is not running, the animation will not be started.
 * @param targetFrameNumber the frame number to jump to
 */
public void jumpToFrame(int targetFrameNumber){
  if (mAnimationBackend == null || mFrameScheduler == null) {
    return;
  }
  mLastFrameAnimationTimeMs=mFrameScheduler.getTargetRenderTimeMs(targetFrameNumber);
  mStartTimeMs=now() - mLastFrameAnimationTimeMs;
  mExpectedRenderTimeMs=mStartTimeMs;
  invalidateSelf();
}
