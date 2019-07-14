public void initRatingAnimation(){
  ratingAnimator=ValueAnimator.ofInt(0,9);
  ratingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animation){
      ratingGap=(int)animation.getAnimatedValue();
      invalidate();
    }
  }
);
  ratingAnimator.setDuration(RATING_ANIMATION_DURATION);
  ratingAnimator.setInterpolator(new LinearInterpolator());
  rotateAnimator.addListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animation){
      if (mAnimatorListener != null) {
        mAnimatorListener.onRatingStart();
      }
    }
    @Override public void onAnimationEnd(    Animator animation){
      isShowCenterTitle=true;
      if (mAnimatorListener != null) {
        mAnimatorListener.onRatingEnd();
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
);
}
