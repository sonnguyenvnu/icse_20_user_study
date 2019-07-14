private void changeBackGroundColor(){
  int colorTo;
  int colorFrom;
  if (fullScreenMode) {
    colorFrom=getBackgroundColor();
    colorTo=(ContextCompat.getColor(SingleMediaActivity.this,R.color.md_black_1000));
  }
 else {
    colorFrom=(ContextCompat.getColor(SingleMediaActivity.this,R.color.md_black_1000));
    colorTo=getBackgroundColor();
  }
  ValueAnimator colorAnimation=ValueAnimator.ofObject(new ArgbEvaluator(),colorFrom,colorTo);
  colorAnimation.setDuration(240);
  colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animator){
      activityBackground.setBackgroundColor((Integer)animator.getAnimatedValue());
    }
  }
);
  colorAnimation.start();
}
