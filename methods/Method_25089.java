/** 
 * Sets whether open and close actions should be animated
 * @param animated if <b>false</b> - menu items will appear/disappear instantly without any animation
 */
public void setAnimated(boolean animated){
  mIsAnimated=animated;
  mOpenAnimatorSet.setDuration(animated ? ANIMATION_DURATION : 0);
  mCloseAnimatorSet.setDuration(animated ? ANIMATION_DURATION : 0);
}
