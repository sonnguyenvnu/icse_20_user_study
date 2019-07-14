@Override public void onHideCustomView(){
  if (mXCustomView == null)   return;
  mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  mXCustomView.setVisibility(View.GONE);
  if (mActivity.getVideoFullView() != null) {
    mActivity.getVideoFullView().removeView(mXCustomView);
  }
  mXCustomView=null;
  mIWebPageView.hindVideoFullView();
  mXCustomViewCallback.onCustomViewHidden();
  mIWebPageView.showWebView();
}
