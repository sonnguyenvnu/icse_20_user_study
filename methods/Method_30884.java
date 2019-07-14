@SuppressLint("SetJavaScriptEnabled") private void setupWebView(){
  WebSettings webSettings=mWebView.getSettings();
  webSettings.setBuiltInZoomControls(true);
  webSettings.setDisplayZoomControls(false);
  webSettings.setDomStorageEnabled(true);
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
    webSettings.setDatabasePath(WebViewUtils.getDatabasePath(this));
  }
  webSettings.setDatabaseEnabled(true);
  webSettings.setLoadWithOverviewMode(true);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
  }
  webSettings.setJavaScriptEnabled(true);
  initializeUserAgents();
  updateUserAgent();
  webSettings.setUseWideViewPort(true);
  mWebView.setWebChromeClient(new ChromeClient());
  mWebView.setWebViewClient(new ViewClient());
  mWebView.setDownloadListener((url,userAgent,contentDisposition,mimeType,contentLength) -> download(url,userAgent,contentDisposition,mimeType));
  onLoadUri(mWebView);
}
