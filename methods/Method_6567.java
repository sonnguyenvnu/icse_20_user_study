private void checkAlphaAnimation(boolean skip){
  if (manualAlphaAnimator) {
    return;
  }
  if (currentAlpha != 1) {
    if (!skip) {
      long currentTime=System.currentTimeMillis();
      long dt=currentTime - lastUpdateAlphaTime;
      if (dt > 18) {
        dt=18;
      }
      currentAlpha+=dt / 150.0f;
      if (currentAlpha > 1) {
        currentAlpha=1;
        if (crossfadeImage != null) {
          recycleBitmap(null,2);
          crossfadeShader=null;
        }
      }
    }
    lastUpdateAlphaTime=System.currentTimeMillis();
    if (parentView != null) {
      if (invalidateAll) {
        parentView.invalidate();
      }
 else {
        parentView.invalidate(imageX,imageY,imageX + imageW,imageY + imageH);
      }
    }
  }
}
