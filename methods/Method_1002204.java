@Override public WebResourceResponse intercept(String url){
  mCurrentUrl=url;
  prepareResponseStream(url);
  parseAndOpenExoPlayer();
  return null;
}
