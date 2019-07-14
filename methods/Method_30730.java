public void hideImmediately(){
  if (!mShowing) {
    return;
  }
  float hideTranslationY=getHideTranslationY();
  if (hideTranslationY != 0) {
    mShowing=false;
    setTranslationY(hideTranslationY);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      mShadowCompatView.setAlpha(0);
    }
 else {
      mAppbarView.setTranslationZ(-mAppbarView.getElevation());
    }
  }
 else {
    ViewUtils.postOnPreDraw(this,new Runnable(){
      @Override public void run(){
        hideImmediately();
      }
    }
);
  }
}
