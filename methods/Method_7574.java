private void startLayoutAnimation(final boolean open,final boolean first,final boolean preview){
  if (first) {
    animationProgress=0.0f;
    lastFrameTime=System.nanoTime() / 1000000;
  }
  AndroidUtilities.runOnUIThread(animationRunnable=new Runnable(){
    @Override public void run(){
      if (animationRunnable != this) {
        return;
      }
      animationRunnable=null;
      if (first) {
        transitionAnimationStartTime=System.currentTimeMillis();
      }
      long newTime=System.nanoTime() / 1000000;
      long dt=newTime - lastFrameTime;
      if (dt > 18) {
        dt=18;
      }
      lastFrameTime=newTime;
      animationProgress+=dt / 150.0f;
      if (animationProgress > 1.0f) {
        animationProgress=1.0f;
      }
      float interpolated=decelerateInterpolator.getInterpolation(animationProgress);
      if (open) {
        containerView.setAlpha(interpolated);
        if (preview) {
          containerView.setScaleX(0.9f + 0.1f * interpolated);
          containerView.setScaleY(0.9f + 0.1f * interpolated);
          previewBackgroundDrawable.setAlpha((int)(0x80 * interpolated));
          Theme.moveUpDrawable.setAlpha((int)(255 * interpolated));
          containerView.invalidate();
          invalidate();
        }
 else {
          containerView.setTranslationX(AndroidUtilities.dp(48) * (1.0f - interpolated));
        }
      }
 else {
        containerViewBack.setAlpha(1.0f - interpolated);
        if (preview) {
          containerViewBack.setScaleX(0.9f + 0.1f * (1.0f - interpolated));
          containerViewBack.setScaleY(0.9f + 0.1f * (1.0f - interpolated));
          previewBackgroundDrawable.setAlpha((int)(0x80 * (1.0f - interpolated)));
          Theme.moveUpDrawable.setAlpha((int)(255 * (1.0f - interpolated)));
          containerView.invalidate();
          invalidate();
        }
 else {
          containerViewBack.setTranslationX(AndroidUtilities.dp(48) * interpolated);
        }
      }
      if (animationProgress < 1) {
        startLayoutAnimation(open,false,preview);
      }
 else {
        onAnimationEndCheck(false);
      }
    }
  }
);
}
