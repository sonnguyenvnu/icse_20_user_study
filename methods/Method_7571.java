public boolean checkTransitionAnimation(){
  if (transitionAnimationPreviewMode) {
    return false;
  }
  if (transitionAnimationInProgress && transitionAnimationStartTime < System.currentTimeMillis() - 1500) {
    onAnimationEndCheck(true);
  }
  return transitionAnimationInProgress;
}
