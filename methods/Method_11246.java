public void initRotatingAnimation(){
  rotateAnimator=ValueAnimator.ofFloat(0.0f,360f * 3);
  rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animation){
      rotateAngle=(float)animation.getAnimatedValue();
      invalidate();
    }
  }
);
  rotateAnimator.setDuration(ROTATING_ANIMATION_DURATION);
  rotateAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
  rotateAnimator.addListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animation){
      if (mAnimatorListener != null) {
        mAnimatorListener.onRotateStart();
      }
    }
    @Override public void onAnimationEnd(    Animator animation){
      isShowCenterTitle=true;
      if (mAnimatorListener != null) {
        mAnimatorListener.onRotateEnd();
      }
      titleAnimator.start();
      ratingAnimator.start();
    }
    @Override public void onAnimationCancel(    Animator animation){
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
);
}
