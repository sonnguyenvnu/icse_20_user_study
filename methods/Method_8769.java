private void updateAnimation(){
  long newTime=System.currentTimeMillis();
  long dt=newTime - lastUpdateTime;
  lastUpdateTime=newTime;
  if (animatedProgressValue != 1 && animatedProgressValue != currentProgress) {
    float progressDiff=currentProgress - animationProgressStart;
    if (progressDiff > 0) {
      currentProgressTime+=dt;
      if (currentProgressTime >= 300) {
        animatedProgressValue=currentProgress;
        animationProgressStart=currentProgress;
        currentProgressTime=0;
      }
 else {
        animatedProgressValue=animationProgressStart + progressDiff * decelerateInterpolator.getInterpolation(currentProgressTime / 300.0f);
      }
    }
    parentView.invalidate();
  }
  if (animatedProgressValue >= 1 && animatedProgressValue == 1 && animatedAlphaValue != 0) {
    animatedAlphaValue-=dt / 200.0f;
    if (animatedAlphaValue <= 0) {
      animatedAlphaValue=0.0f;
    }
    parentView.invalidate();
  }
}
