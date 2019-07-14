@SuppressLint("SetJavaScriptEnabled") private void initView(){
  webView=findViewById(R.id.webView);
  setTitle("??(V" + getVersionName(this) + ")");
  settings=webView.getSettings();
  settings.setJavaScriptEnabled(true);
  settings.setJavaScriptCanOpenWindowsAutomatically(true);
  settings.setSupportZoom(true);
  settings.setBuiltInZoomControls(true);
  settings.setDisplayZoomControls(false);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
    settings.setLoadsImagesAutomatically(true);
  }
 else {
    webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    settings.setLoadsImagesAutomatically(false);
  }
  settings.setUseWideViewPort(true);
  settings.setLoadWithOverviewMode(true);
  settings.setDomStorageEnabled(true);
  settings.setSaveFormData(true);
  settings.setSupportMultipleWindows(true);
  settings.setAppCacheEnabled(true);
  settings.setCacheMode(WebSettings.LOAD_DEFAULT);
  webView.setHorizontalScrollbarOverlay(true);
  webView.setHorizontalScrollBarEnabled(false);
  webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
  webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
  webView.requestFocus();
  webView.loadUrl("file:///android_asset/about.html");
  webView.setWebViewClient(new WebViewClient(){
    @Override public boolean shouldOverrideUrlLoading(    WebView view,    String url){
      view.loadUrl(url);
      return true;
    }
  }
);
}
