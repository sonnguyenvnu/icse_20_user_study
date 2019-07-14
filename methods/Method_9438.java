@Override public boolean onBackPressed(){
  if (shouldNavigateBack) {
    webView.loadUrl(webViewUrl);
    shouldNavigateBack=false;
    return false;
  }
  return !donePressed;
}
