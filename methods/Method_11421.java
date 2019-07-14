private void startAnimator(float... value){
  if (mRatioAnimator == null) {
    mRatioAnimator=new ValueAnimator();
  }
  mRatioAnimator.cancel();
  mRatioAnimator.setFloatValues(value);
  mRatioAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator value){
      mRatio=(float)value.getAnimatedValue();
      invalidate();
    }
  }
);
  mRatioAnimator.start();
}
