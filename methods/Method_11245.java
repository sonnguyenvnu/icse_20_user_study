private void initTextAnimation(){
  titleAnimator=ValueAnimator.ofInt(0,255);
  titleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animation){
      textAlpha=(int)animation.getAnimatedValue();
      invalidate();
    }
  }
);
  titleAnimator.setDuration(TEXT_ANIMATION_DURATION);
  titleAnimator.setInterpolator(new AccelerateInterpolator());
}
