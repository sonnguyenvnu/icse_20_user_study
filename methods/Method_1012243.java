@Override public void show(){
  ValueAnimator va=ValueAnimator.ofFloat(mTarget.getScaleX(),1);
  va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator valueAnimator){
      float scale=(Float)valueAnimator.getAnimatedValue();
      mTarget.setScaleX(scale);
      mTarget.setScaleY(scale);
    }
  }
);
  va.setDuration(300);
  va.start();
  mCurrentState=STATE_SHOW;
}
