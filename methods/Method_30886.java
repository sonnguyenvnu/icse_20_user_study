private void toggleRequestDesktopSite(){
  Settings.REQUEST_DESKTOP_SITE_IN_WEBVIEW.putValue(!Settings.REQUEST_DESKTOP_SITE_IN_WEBVIEW.getValue());
  updateRequestDesktopSite();
  updateUserAgent();
}
