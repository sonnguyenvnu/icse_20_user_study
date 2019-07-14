@Override public boolean onKeyDown(int keyCode,KeyEvent event){
  if (keyCode == KeyEvent.KEYCODE_BACK) {
    if (mWebChromeClient.inCustomView()) {
      hideCustomView();
      return true;
    }
 else     if (webView.canGoBack()) {
      webView.goBack();
      return true;
    }
 else {
      handleFinish();
    }
  }
  return false;
}
