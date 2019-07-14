@JavascriptInterface public void startIntercept(){
  if (prettifyWebView != null) {
    prettifyWebView.setInterceptTouch(true);
    if (toggleNestScrolling)     prettifyWebView.setEnableNestedScrolling(false);
  }
}
