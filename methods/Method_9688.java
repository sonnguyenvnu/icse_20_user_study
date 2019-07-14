@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && !backward && webView != null) {
    webView.loadUrl(currentUrl);
  }
}
