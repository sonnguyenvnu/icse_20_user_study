protected boolean shouldOverrideUrlLoading(WebView webView,String url){
  Uri uri=Uri.parse(url);
  if (mDisableLoadOverridingUrls.contains(UriUtils.withoutQueryAndFragment(uri).toString())) {
    return false;
  }
  return (Settings.OPEN_WITH_NATIVE_IN_WEBVIEW.getValue() && DoubanUriHandler.open(uri,this)) || FrodoBridge.openFrodoUri(uri,this) || (Settings.PROGRESSIVE_THIRD_PARTY_APP.getValue() && FrodoBridge.openUri(uri,this));
}
