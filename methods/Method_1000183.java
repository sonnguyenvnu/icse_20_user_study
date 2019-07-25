public <V extends View & ShimmerViewBase>void start(final V shimmerView){
  if (isAnimating()) {
    return;
  }
  final Runnable animate=new Runnable(){
    @Override public void run(){
      shimmerView.setShimmering(true);
      float fromX=0;
      float toX=shimmerView.getWidth();
      if (direction == ANIMATION_DIRECTION_RTL) {
        fromX=shimmerView.getWidth();
        toX=0;
      }
      animator=ObjectAnimator.ofFloat(shimmerView,"gradientX",fromX,toX);
      animator.setRepeatCount(repeatCount);
      animator.setDuration(duration);
      animator.setStartDelay(startDelay);
      animator.addListener(new Animator.AnimatorListener(){
        @Override public void onAnimationStart(        Animator animation){
        }
        @Override public void onAnimationEnd(        Animator animation){
          shimmerView.setShimmering(false);
          if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            shimmerView.postInvalidate();
          }
 else {
            shimmerView.postInvalidateOnAnimation();
          }
          animator=null;
        }
        @Override public void onAnimationCancel(        Animator animation){
        }
        @Override public void onAnimationRepeat(        Animator animation){
        }
      }
);
      if (animatorListener != null) {
        animator.addListener(animatorListener);
      }
      animator.start();
    }
  }
;
  if (!shimmerView.isSetUp()) {
    shimmerView.setAnimationSetupCallback(new ShimmerViewHelper.AnimationSetupCallback(){
      @Override public void onSetupAnimation(      final View target){
        animate.run();
      }
    }
);
  }
 else {
    animate.run();
  }
}
