private boolean notifyPlaylistError(HlsUrl playlistUrl,long blacklistDurationMs){
  int listenersSize=listeners.size();
  boolean anyBlacklistingFailed=false;
  for (int i=0; i < listenersSize; i++) {
    anyBlacklistingFailed|=!listeners.get(i).onPlaylistError(playlistUrl,blacklistDurationMs);
  }
  return anyBlacklistingFailed;
}
