public boolean checkTransitionAnimation(){
  if (animationInProgress && animationStartTime < System.currentTimeMillis() - 400) {
    animationInProgress=false;
    if (onAnimationEndRunnable != null) {
      onAnimationEndRunnable.run();
      onAnimationEndRunnable=null;
    }
  }
  return animationInProgress;
}
