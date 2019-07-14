public void pauseRotateAnimation(){
  mLastAnimationValue=mRotateAnimator.getCurrentPlayTime();
  mRotateAnimator.cancel();
}
