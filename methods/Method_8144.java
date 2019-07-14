public void setBackgroundColorAnimated(boolean checked,int color){
  if (animator != null) {
    animator.cancel();
    animator=null;
  }
  if (animatedColorBackground != 0) {
    setBackgroundColor(animatedColorBackground);
  }
  if (animationPaint == null) {
    animationPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
  }
  checkBox.setOverrideColor(checked ? 1 : 2);
  animatedColorBackground=color;
  animationPaint.setColor(animatedColorBackground);
  animationProgress=0.0f;
  animator=ObjectAnimator.ofFloat(this,ANIMATION_PROGRESS,0.0f,1.0f);
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      setBackgroundColor(animatedColorBackground);
      animatedColorBackground=0;
      invalidate();
    }
  }
);
  animator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
  animator.setDuration(240).start();
}
