public void hide(){
  if (!mShowing) {
    return;
  }
  mShowing=false;
  cancelAnimator();
  mAnimator=new AnimatorSet().setDuration(mAnimationDuration);
  mAnimator.setInterpolator(new FastOutLinearInInterpolator());
  AnimatorSet.Builder builder=mAnimator.play(ObjectAnimator.ofFloat(this,TRANSLATION_Y,getTranslationY(),getHideTranslationY()));
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    builder.before(ObjectAnimator.ofFloat(mShadowCompatView,ALPHA,mShadowCompatView.getAlpha(),0));
  }
 else {
    builder.before(ObjectAnimator.ofFloat(mAppbarView,TRANSLATION_Z,mAppbarView.getTranslationZ(),-mAppbarView.getElevation()));
  }
  startAnimator();
}
