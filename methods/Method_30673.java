@TargetApi(Build.VERSION_CODES.KITKAT_WATCH) @Override public WindowInsets dispatchApplyWindowInsets(WindowInsets insets){
  setPadding(insets.getSystemWindowInsetLeft(),0,insets.getSystemWindowInsetRight(),0);
  mProfileHeaderLayout.setInsetTop(insets.getSystemWindowInsetTop());
  return insets.consumeSystemWindowInsets();
}
