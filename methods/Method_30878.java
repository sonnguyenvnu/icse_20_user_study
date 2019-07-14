protected void onLoadUri(WebView webView){
  String url=getIntent().getData().toString();
  if (Settings.REQUEST_DESKTOP_SITE_IN_WEBVIEW.getValue()) {
    url=getDoubanDesktopSiteUrl(url);
  }
  Map<String,String> headers=null;
  if (isDoubanUrl(url)) {
    Account account=AccountUtils.getActiveAccount();
    if (account != null) {
      String authToken=AccountUtils.peekAuthToken(account,AccountContract.AUTH_TOKEN_TYPE_FRODO);
      if (!TextUtils.isEmpty(authToken)) {
        url=StringUtils.formatUs(DOUBAN_OAUTH2_REDIRECT_URL_FORMAT,Uri.encode(url),Uri.encode(ApiCredential.ApiV2.KEY));
        headers=new HashMap<>();
        headers.put(Http.Headers.AUTHORIZATION,Http.Headers.makeBearerAuthorization(authToken));
      }
    }
  }
  webView.loadUrl(url,headers);
}
