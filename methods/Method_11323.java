private void initAutoFitEditText(){
  mTvTitle.clearFocus();
  mTvTitle.setEnabled(false);
  mTvTitle.setFocusableInTouchMode(false);
  mTvTitle.setFocusable(false);
  mTvTitle.setEnableSizeCache(false);
  mTvTitle.setMovementMethod(null);
  mTvTitle.setMaxHeight(RxImageTool.dip2px(55f));
  mTvTitle.setMinTextSize(37f);
  try {
    RxTextAutoZoom.setNormalization((Activity)getContext(),mRootLayout,mTvTitle);
    RxKeyboardTool.hideSoftInput((Activity)getContext());
  }
 catch (  Exception e) {
  }
}
