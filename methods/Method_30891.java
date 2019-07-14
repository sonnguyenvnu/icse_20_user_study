private void openInBrowser(){
  String url=mWebView.getUrl();
  if (!TextUtils.isEmpty(url)) {
    UrlUtils.openWithIntent(url,this);
  }
 else {
    ToastUtils.show(R.string.webview_error_url_empty,this);
  }
}
