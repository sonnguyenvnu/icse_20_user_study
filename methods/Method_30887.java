private void updateRequestDesktopSite(){
  if (mRequestDesktopSiteMenuItem == null) {
    return;
  }
  mRequestDesktopSiteMenuItem.setChecked(Settings.REQUEST_DESKTOP_SITE_IN_WEBVIEW.getValue());
}
