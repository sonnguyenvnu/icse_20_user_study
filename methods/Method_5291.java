/** 
 * For live streaming with emsg event stream, forward seeking can seek pass the emsg messages that signals end-of-stream or Manifest expiry, which results in load error. In this case, we should notify the Dash media source to refresh its manifest.
 * @param chunk The chunk whose load encountered the error.
 * @return True if manifest refresh has been requested, false otherwise.
 */
boolean maybeRefreshManifestOnLoadingError(Chunk chunk){
  if (!manifest.dynamic) {
    return false;
  }
  if (isWaitingForManifestRefresh) {
    return true;
  }
  boolean isAfterForwardSeek=lastLoadedChunkEndTimeUs != C.TIME_UNSET && lastLoadedChunkEndTimeUs < chunk.startTimeUs;
  if (isAfterForwardSeek) {
    maybeNotifyDashManifestRefreshNeeded();
    return true;
  }
  return false;
}
