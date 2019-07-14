void absorbGlows(int velocityX,int velocityY){
  if (velocityX < 0) {
    ensureLeftGlow();
    if (mLeftGlow.isFinished()) {
      mLeftGlow.onAbsorb(-velocityX);
    }
  }
 else   if (velocityX > 0) {
    ensureRightGlow();
    if (mRightGlow.isFinished()) {
      mRightGlow.onAbsorb(velocityX);
    }
  }
  if (velocityY < 0) {
    ensureTopGlow();
    if (mTopGlow.isFinished()) {
      mTopGlow.onAbsorb(-velocityY);
    }
  }
 else   if (velocityY > 0) {
    ensureBottomGlow();
    if (mBottomGlow.isFinished()) {
      mBottomGlow.onAbsorb(velocityY);
    }
  }
  if (velocityX != 0 || velocityY != 0) {
    ViewCompat.postInvalidateOnAnimation(this);
  }
}
