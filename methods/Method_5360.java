@Override public boolean onPlaylistError(HlsUrl url,long blacklistDurationMs){
  boolean noBlacklistingFailure=true;
  for (  HlsSampleStreamWrapper streamWrapper : sampleStreamWrappers) {
    noBlacklistingFailure&=streamWrapper.onPlaylistError(url,blacklistDurationMs);
  }
  callback.onContinueLoadingRequested(this);
  return noBlacklistingFailure;
}
