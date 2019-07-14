private int getMinHeight(){
  if (mUseWideLayout) {
    return mMaxHeight;
  }
 else {
    return mToolbar.getLayoutParams().height + mInsetTop;
  }
}
