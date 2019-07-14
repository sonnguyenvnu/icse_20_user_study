/** 
 * Requests DASH media manifest to be refreshed if necessary. 
 */
private void maybeNotifyDashManifestRefreshNeeded(){
  if (lastLoadedChunkEndTimeBeforeRefreshUs != C.TIME_UNSET && lastLoadedChunkEndTimeBeforeRefreshUs == lastLoadedChunkEndTimeUs) {
    return;
  }
  isWaitingForManifestRefresh=true;
  lastLoadedChunkEndTimeBeforeRefreshUs=lastLoadedChunkEndTimeUs;
  playerEmsgCallback.onDashManifestRefreshRequested();
}
