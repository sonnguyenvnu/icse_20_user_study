@Override public WebResourceResponse intercept(String url){
  if (!test(url)) {
    return null;
  }
  if (url.contains("get_video_info")) {
    return cleanupDashInfo(url);
  }
  pressBackButton();
  openExternalPlayer(url);
  return null;
}
