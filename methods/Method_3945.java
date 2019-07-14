void considerReleasingGlowsOnScroll(int dx,int dy){
  boolean needsInvalidate=false;
  if (mLeftGlow != null && !mLeftGlow.isFinished() && dx > 0) {
    mLeftGlow.onRelease();
    needsInvalidate=mLeftGlow.isFinished();
  }
  if (mRightGlow != null && !mRightGlow.isFinished() && dx < 0) {
    mRightGlow.onRelease();
    needsInvalidate|=mRightGlow.isFinished();
  }
  if (mTopGlow != null && !mTopGlow.isFinished() && dy > 0) {
    mTopGlow.onRelease();
    needsInvalidate|=mTopGlow.isFinished();
  }
  if (mBottomGlow != null && !mBottomGlow.isFinished() && dy < 0) {
    mBottomGlow.onRelease();
    needsInvalidate|=mBottomGlow.isFinished();
  }
  if (needsInvalidate) {
    ViewCompat.postInvalidateOnAnimation(this);
  }
}
