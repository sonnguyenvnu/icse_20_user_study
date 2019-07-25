@Override public boolean test(String url){
  if (url.contains(ExoInterceptor.URL_VIDEO_DATA)) {
    mCurrentInterceptor=mExoInterceptor;
    return true;
  }
  if (url.contains("tv-player")) {
    mCurrentInterceptor=mCipherInterceptor;
    return true;
  }
  if (url.contains("ptracking")) {
    mCurrentInterceptor=mDoOnPlayEndInterceptor;
    return true;
  }
  return false;
}
