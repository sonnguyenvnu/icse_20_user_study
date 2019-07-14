@Override public boolean onChunkLoadError(Chunk chunk,boolean cancelable,Exception e,long blacklistDurationMs){
  if (!cancelable) {
    return false;
  }
  if (playerTrackEmsgHandler != null && playerTrackEmsgHandler.maybeRefreshManifestOnLoadingError(chunk)) {
    return true;
  }
  if (!manifest.dynamic && chunk instanceof MediaChunk && e instanceof InvalidResponseCodeException && ((InvalidResponseCodeException)e).responseCode == 404) {
    RepresentationHolder representationHolder=representationHolders[trackSelection.indexOf(chunk.trackFormat)];
    int segmentCount=representationHolder.getSegmentCount();
    if (segmentCount != DashSegmentIndex.INDEX_UNBOUNDED && segmentCount != 0) {
      long lastAvailableSegmentNum=representationHolder.getFirstSegmentNum() + segmentCount - 1;
      if (((MediaChunk)chunk).getNextChunkIndex() > lastAvailableSegmentNum) {
        missingLastSegment=true;
        return true;
      }
    }
  }
  return blacklistDurationMs != C.TIME_UNSET && trackSelection.blacklist(trackSelection.indexOf(chunk.trackFormat),blacklistDurationMs);
}
