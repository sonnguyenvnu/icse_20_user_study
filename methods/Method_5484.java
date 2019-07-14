@Override public boolean onChunkLoadError(Chunk chunk,boolean cancelable,Exception e,long blacklistDurationMs){
  return cancelable && blacklistDurationMs != C.TIME_UNSET && trackSelection.blacklist(trackSelection.indexOf(chunk.trackFormat),blacklistDurationMs);
}
