void animate(boolean schedule){
  mHasAlpha=true;
  final long now=SystemClock.uptimeMillis();
  boolean animating=false;
  if (mCurrDrawable != null) {
    if (mEnterAnimationEnd != 0) {
      if (mEnterAnimationEnd <= now) {
        mCurrDrawable.setAlpha(mAlpha);
        mEnterAnimationEnd=0;
      }
 else {
        int animAlpha=(int)((mEnterAnimationEnd - now) * 255) / mDrawableContainerState.mEnterFadeDuration;
        mCurrDrawable.setAlpha(((255 - animAlpha) * mAlpha) / 255);
        animating=true;
      }
    }
  }
 else {
    mEnterAnimationEnd=0;
  }
  if (mLastDrawable != null) {
    if (mExitAnimationEnd != 0) {
      if (mExitAnimationEnd <= now) {
        mLastDrawable.setVisible(false,false);
        mLastDrawable=null;
        mLastIndex=-1;
        mExitAnimationEnd=0;
      }
 else {
        int animAlpha=(int)((mExitAnimationEnd - now) * 255) / mDrawableContainerState.mExitFadeDuration;
        mLastDrawable.setAlpha((animAlpha * mAlpha) / 255);
        animating=true;
      }
    }
  }
 else {
    mExitAnimationEnd=0;
  }
  if (schedule && animating) {
    scheduleSelf(mAnimationRunnable,now + 1000 / 60);
  }
}
