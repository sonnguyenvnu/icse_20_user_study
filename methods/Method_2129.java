@SuppressLint("NewApi") @Override public void setTransformAnimated(final Matrix newTransform,long durationMs,@Nullable final Runnable onAnimationComplete){
  FLog.v(getLogTag(),"setTransformAnimated: duration %d ms",durationMs);
  stopAnimation();
  Preconditions.checkArgument(durationMs > 0);
  Preconditions.checkState(!isAnimating());
  setAnimating(true);
  mValueAnimator.setDuration(durationMs);
  getTransform().getValues(getStartValues());
  newTransform.getValues(getStopValues());
  mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator valueAnimator){
      calculateInterpolation(getWorkingTransform(),(float)valueAnimator.getAnimatedValue());
      AnimatedZoomableController.super.setTransform(getWorkingTransform());
    }
  }
);
  mValueAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationCancel(    Animator animation){
      FLog.v(getLogTag(),"setTransformAnimated: animation cancelled");
      onAnimationStopped();
    }
    @Override public void onAnimationEnd(    Animator animation){
      FLog.v(getLogTag(),"setTransformAnimated: animation finished");
      onAnimationStopped();
    }
    private void onAnimationStopped(){
      if (onAnimationComplete != null) {
        onAnimationComplete.run();
      }
      setAnimating(false);
      getDetector().restartGesture();
    }
  }
);
  mValueAnimator.start();
}
