private boolean isUsingRendererClock(){
  return rendererClockSource != null && !rendererClockSource.isEnded() && (rendererClockSource.isReady() || !rendererClockSource.hasReadStreamToEnd());
}
