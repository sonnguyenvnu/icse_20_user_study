@SuppressWarnings("deprecation") @Override public boolean shouldOverrideUrlLoading(WebView view,String url){
  if (TextUtils.isEmpty(url)) {
    return false;
  }
  if (url.startsWith("http:") || url.startsWith("https:")) {
    if (url.contains(".apk")) {
      handleOtherwise(mActivity,url);
      return true;
    }
    return false;
  }
  handleOtherwise(mActivity,url);
  return true;
}
