/** 
 * Start scrolling by providing a starting point and the distance to travel.
 * @param startX Starting horizontal scroll offset in pixels. Positivenumbers will scroll the content to the left.
 * @param startY Starting vertical scroll offset in pixels. Positive numberswill scroll the content up.
 * @param dx Horizontal distance to travel. Positive numbers will scroll thecontent to the left.
 * @param dy Vertical distance to travel. Positive numbers will scroll thecontent up.
 * @param duration Duration of the scroll in milliseconds.
 */
public void startScroll(int startX,int startY,int dx,int dy,int duration){
  mMode=SCROLL_MODE;
  mFinished=false;
  mDuration=duration;
  mStartTime=AnimationUtils.currentAnimationTimeMillis();
  mStartX=startX;
  mStartY=startY;
  mFinalX=startX + dx;
  mFinalY=startY + dy;
  mDeltaX=dx;
  mDeltaY=dy;
  mDurationReciprocal=1.0f / (float)mDuration;
}
