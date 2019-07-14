@Override public void onDragBottom(boolean rightToLeft){
  if (rightToLeft) {
    if (wvWebView.canGoForward()) {
      wvWebView.goForward();
    }
    return;
  }
  onBackPressed();
}
