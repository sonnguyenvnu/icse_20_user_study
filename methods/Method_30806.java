public void show(){
  if (mShowing) {
    return;
  }
  mShowing=true;
  cancelAnimator();
  mAnimator=ObjectAnimator.ofFloat(this,TRANSLATION_Y,getTranslationY(),0).setDuration(mAnimationDuration);
  mAnimator.setInterpolator(new FastOutSlowInInterpolator());
  mAnimator.start();
}
