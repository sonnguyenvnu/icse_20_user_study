/** 
 * Check if the spring is overshooting beyond its target.
 * @return true if the spring is overshooting its target
 */
public boolean isOvershooting(){
  return mSpringConfig.tension > 0 && ((mStartValue < mEndValue && getCurrentValue() > mEndValue) || (mStartValue > mEndValue && getCurrentValue() < mEndValue));
}
