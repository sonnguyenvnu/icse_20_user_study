private int getAppBarMinHeight(){
  if (mUseWideLayout) {
    return getAppBarMaxHeight();
  }
 else {
    return mToolbar.getLayoutParams().height;
  }
}
