private boolean checkAnimation(){
  if (animationInProgress != 0) {
    if (Math.abs(transitionAnimationStartTime - System.currentTimeMillis()) >= 500) {
      if (animationEndRunnable != null) {
        animationEndRunnable.run();
        animationEndRunnable=null;
      }
      animationInProgress=0;
    }
  }
  return animationInProgress != 0;
}
