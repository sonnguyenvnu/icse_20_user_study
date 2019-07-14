@Override public boolean shouldOverrideUrlLoading(WebView view,String url){
  try {
    url=URLDecoder.decode(url,"UTF-8");
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
  if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) {
    webView.handlerReturnData(url);
    return true;
  }
 else   if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) {
    webView.flushMessageQueue();
    return true;
  }
 else {
    return this.onCustomShouldOverrideUrlLoading(url) ? true : super.shouldOverrideUrlLoading(view,url);
  }
}
