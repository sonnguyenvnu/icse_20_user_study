@JavascriptInterface public void stopIntercept(){
  if (prettifyWebView != null) {
    prettifyWebView.setInterceptTouch(false);
    if (toggleNestScrolling)     prettifyWebView.setEnableNestedScrolling(true);
  }
}
