private void changeBackGroundColor(){
  int colorTo;
  int colorFrom;
  if (fullScreenMode) {
    colorFrom=getBackgroundColor();
    colorTo=(ContextCompat.getColor(PlayerActivity.this,R.color.md_black_1000));
  }
 else {
    colorFrom=(ContextCompat.getColor(PlayerActivity.this,R.color.md_black_1000));
    colorTo=getBackgroundColor();
  }
  ValueAnimator colorAnimation=ValueAnimator.ofObject(new ArgbEvaluator(),colorFrom,colorTo);
  colorAnimation.setDuration(240);
  colorAnimation.addUpdateListener(animator -> rootView.setBackgroundColor((Integer)animator.getAnimatedValue()));
  colorAnimation.start();
}
