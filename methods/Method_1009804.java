/** 
 * ??
 */
public void reset(){
  if (mAnimator != null) {
    mAnimator.cancel();
  }
  mRotateAngle=180 + mEachAngle / 2 + LINE_INTERVAL;
  invalidate();
}
