public void start(){
  if (mAnimator != null) {
    mAnimator.cancel();
  }
  mAnimator=ObjectAnimator.ofFloat(this,"curPointAngle",180 + mEachAngle / 2 + LINE_INTERVAL,mValue * (mEachAngle + LINE_INTERVAL) + 180);
  mAnimator.setDuration(DURATION);
  mAnimator.setInterpolator(new TimeInterpolator(){
    @Override public float getInterpolation(    float x){
      float factor=0.45f;
      return (float)(Math.pow(2,-10 * x) * Math.sin((x - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
  }
);
  mAnimator.start();
}
