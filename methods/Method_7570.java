private void onAnimationEndCheck(boolean byCheck){
  onCloseAnimationEnd();
  onOpenAnimationEnd();
  if (waitingForKeyboardCloseRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(waitingForKeyboardCloseRunnable);
    waitingForKeyboardCloseRunnable=null;
  }
  if (currentAnimation != null) {
    if (byCheck) {
      currentAnimation.cancel();
    }
    currentAnimation=null;
  }
  if (animationRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(animationRunnable);
    animationRunnable=null;
  }
  setAlpha(1.0f);
  containerView.setAlpha(1.0f);
  containerView.setScaleX(1.0f);
  containerView.setScaleY(1.0f);
  containerViewBack.setAlpha(1.0f);
  containerViewBack.setScaleX(1.0f);
  containerViewBack.setScaleY(1.0f);
}
