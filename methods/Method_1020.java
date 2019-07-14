/** 
 * Set the animation to the given level. The level represents the animation time in ms. If the animation time is greater than the last frame time for the last loop, the last frame will be displayed. If the animation is running (e.g. if  {@link #start()} has been called, the level changewill be ignored. In this case,  {@link #stop()} the animation first.
 * @param level the animation time in ms
 * @return true if the level change could be performed
 */
@Override protected boolean onLevelChange(int level){
  if (mIsRunning) {
    return false;
  }
  if (mLastFrameAnimationTimeMs != level) {
    mLastFrameAnimationTimeMs=level;
    invalidateSelf();
    return true;
  }
  return false;
}
