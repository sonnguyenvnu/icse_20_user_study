private void updateUserAgent(){
  boolean requestDesktopSite=Settings.REQUEST_DESKTOP_SITE_IN_WEBVIEW.getValue();
  WebSettings webSettings=mWebView.getSettings();
  String oldUserAgent=webSettings.getUserAgentString();
  boolean changed=false;
  if (requestDesktopSite && !TextUtils.equals(oldUserAgent,mDesktopUserAgent)) {
    webSettings.setUserAgentString(mDesktopUserAgent);
    changed=true;
  }
 else   if (!requestDesktopSite && !TextUtils.equals(oldUserAgent,mDefaultUserAgent)) {
    webSettings.setUserAgentString(mDefaultUserAgent);
    changed=true;
  }
  String url=mWebView.getUrl();
  if (!TextUtils.isEmpty(url) && changed) {
    if (requestDesktopSite) {
      String doubanDesktopSiteUrl=getDoubanDesktopSiteUrl(url);
      if (!TextUtils.equals(url,doubanDesktopSiteUrl)) {
        mWebView.loadUrl(doubanDesktopSiteUrl);
      }
 else {
        mWebView.reload();
      }
    }
 else {
      mWebView.reload();
    }
  }
}
