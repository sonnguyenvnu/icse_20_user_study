@SuppressLint({"SetJavaScriptEnabled","JavascriptInterface"}) @Override public void initData(){
  WebSettings webSettings=wvWebView.getSettings();
  webSettings.setJavaScriptEnabled(true);
  wvWebView.requestFocus();
  wvWebView.setWebChromeClient(new WebChromeClient(){
    @Override public void onReceivedTitle(    WebView view,    String title){
      super.onReceivedTitle(view,title);
      tvBaseTitle.setText(StringUtil.getTrimedString(title));
    }
    @Override public void onProgressChanged(    WebView view,    int newProgress){
      super.onProgressChanged(view,newProgress);
      pbWebView.setProgress(newProgress);
    }
  }
);
  wvWebView.setWebViewClient(new WebViewClient(){
    @Override public boolean shouldOverrideUrlLoading(    WebView view,    String url){
      wvWebView.loadUrl(url);
      return true;
    }
    @Override public void onPageStarted(    WebView view,    String url,    Bitmap favicon){
      super.onPageStarted(view,url,favicon);
      tvBaseTitle.setText(StringUtil.getTrimedString(wvWebView.getUrl()));
      pbWebView.setVisibility(View.VISIBLE);
    }
    @Override public void onPageFinished(    WebView view,    String url){
      super.onPageFinished(view,url);
      tvBaseTitle.setText(StringUtil.getTrimedString(wvWebView.getTitle()));
      pbWebView.setVisibility(View.GONE);
    }
  }
);
  wvWebView.loadUrl(url);
}
