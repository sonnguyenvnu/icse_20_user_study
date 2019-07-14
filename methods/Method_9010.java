/** 
 * Returns the time elapsed since the beginning of the scrolling.
 * @return The elapsed time in milliseconds.
 */
public int timePassed(){
  return (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
}
