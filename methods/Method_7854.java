private void checkScrollAnimated(){
  int maxHeight=AndroidUtilities.dp(56);
  if (currentHeaderHeight == maxHeight) {
    return;
  }
  ValueAnimator va=ValueAnimator.ofObject(new IntEvaluator(),currentHeaderHeight,AndroidUtilities.dp(56)).setDuration(180);
  va.setInterpolator(new DecelerateInterpolator());
  va.addUpdateListener(animation -> setCurrentHeaderHeight((int)animation.getAnimatedValue()));
  va.start();
}
