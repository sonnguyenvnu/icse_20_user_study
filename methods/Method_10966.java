private void initData(){
  pbWebBase.setMax(100);
  webPath=RxConstants.URL_BAIDU_SEARCH;
  if (webPath.equals("")) {
    webPath="http://www.baidu.com";
  }
  WebSettings webSettings=webBase.getSettings();
  if (Build.VERSION.SDK_INT >= 19) {
    webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
  }
  if (Build.VERSION.SDK_INT >= 19) {
    webSettings.setLoadsImagesAutomatically(true);
  }
 else {
    webSettings.setLoadsImagesAutomatically(false);
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    webBase.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
  }
  webBase.setLayerType(View.LAYER_TYPE_HARDWARE,null);
  webSettings.setJavaScriptEnabled(true);
  webSettings.setSupportZoom(true);
  webSettings.setBuiltInZoomControls(true);
  webSettings.setDisplayZoomControls(false);
  webSettings.setUseWideViewPort(true);
  webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
  webSettings.setLoadWithOverviewMode(true);
  webSettings.setDatabaseEnabled(true);
  webSettings.setSavePassword(true);
  webSettings.setDomStorageEnabled(true);
  webBase.setSaveEnabled(true);
  webBase.setKeepScreenOn(true);
  webBase.setWebChromeClient(new WebChromeClient(){
    @Override public void onReceivedTitle(    WebView view,    String title){
      super.onReceivedTitle(view,title);
      mRxTextAutoZoom.setText(title);
    }
    @Override public void onProgressChanged(    WebView view,    int newProgress){
      pbWebBase.setProgress(newProgress);
      super.onProgressChanged(view,newProgress);
    }
  }
);
  webBase.setWebViewClient(new WebViewClient(){
    @Override public void onPageFinished(    WebView view,    String url){
      super.onPageFinished(view,url);
      if (!webBase.getSettings().getLoadsImagesAutomatically()) {
        webBase.getSettings().setLoadsImagesAutomatically(true);
      }
      pbWebBase.setVisibility(View.GONE);
    }
    @Override public void onPageStarted(    WebView view,    String url,    Bitmap favicon){
      pbWebBase.setVisibility(View.VISIBLE);
      super.onPageStarted(view,url,favicon);
    }
    @Override public boolean shouldOverrideUrlLoading(    WebView view,    String url){
      if (url.startsWith("http:") || url.startsWith("https:")) {
        view.loadUrl(url);
        return false;
      }
      try {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(intent);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
      return true;
    }
  }
);
  webBase.setDownloadListener(new DownloadListener(){
    @Override public void onDownloadStart(    String paramAnonymousString1,    String paramAnonymousString2,    String paramAnonymousString3,    String paramAnonymousString4,    long paramAnonymousLong){
      Intent intent=new Intent();
      intent.setAction("android.intent.action.VIEW");
      intent.setData(Uri.parse(paramAnonymousString1));
      startActivity(intent);
    }
  }
);
  webBase.loadUrl(webPath);
  Log.v("???????",webPath);
}
