public void show(){
  if (mShowing) {
    return;
  }
  mShowing=true;
  cancelAnimator();
  mAnimator=new AnimatorSet().setDuration(mAnimationDuration);
  mAnimator.setInterpolator(new FastOutSlowInInterpolator());
  AnimatorSet.Builder builder=mAnimator.play(ObjectAnimator.ofFloat(this,TRANSLATION_Y,getTranslationY(),0));
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    builder.with(ObjectAnimator.ofFloat(mShadowCompatView,ALPHA,mShadowCompatView.getAlpha(),1));
  }
 else {
    builder.with(ObjectAnimator.ofFloat(mAppbarView,TRANSLATION_Z,mAppbarView.getTranslationZ(),0));
  }
  startAnimator();
}
