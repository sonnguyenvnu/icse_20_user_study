public void initAutoFitEditText(){
  mRxTextAutoZoom.clearFocus();
  mRxTextAutoZoom.setEnabled(false);
  mRxTextAutoZoom.setFocusableInTouchMode(false);
  mRxTextAutoZoom.setFocusable(false);
  mRxTextAutoZoom.setEnableSizeCache(false);
  mRxTextAutoZoom.setMovementMethod(null);
  mRxTextAutoZoom.setMaxHeight(RxImageTool.dip2px(55f));
  mRxTextAutoZoom.setMinTextSize(37f);
  RxTextAutoZoom.setNormalization(this,llIncludeTitle,mRxTextAutoZoom);
  RxKeyboardTool.hideSoftInput(this);
}
