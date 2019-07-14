public void showImmediately(){
  if (mShowing) {
    return;
  }
  mShowing=true;
  setTranslationY(0);
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    mShadowCompatView.setAlpha(1);
  }
 else {
    mAppbarView.setTranslationZ(0);
  }
}
