public void stop(){
  if (mAnimator != null) {
    mAnimator.removeUpdateListener(mUpdateListener);
    mAnimator.removeAllUpdateListeners();
    mAnimator.cancel();
    mAnimator=null;
  }
}
