@Override public boolean continueLoading(long positionUs){
  if (loadingFinished || loader.isLoading()) {
    return false;
  }
  List<HlsMediaChunk> chunkQueue;
  long loadPositionUs;
  if (isPendingReset()) {
    chunkQueue=Collections.emptyList();
    loadPositionUs=pendingResetPositionUs;
  }
 else {
    chunkQueue=readOnlyMediaChunks;
    HlsMediaChunk lastMediaChunk=getLastMediaChunk();
    loadPositionUs=lastMediaChunk.isLoadCompleted() ? lastMediaChunk.endTimeUs : Math.max(lastSeekPositionUs,lastMediaChunk.startTimeUs);
  }
  chunkSource.getNextChunk(positionUs,loadPositionUs,chunkQueue,nextChunkHolder);
  boolean endOfStream=nextChunkHolder.endOfStream;
  Chunk loadable=nextChunkHolder.chunk;
  HlsMasterPlaylist.HlsUrl playlistToLoad=nextChunkHolder.playlist;
  nextChunkHolder.clear();
  if (endOfStream) {
    pendingResetPositionUs=C.TIME_UNSET;
    loadingFinished=true;
    return true;
  }
  if (loadable == null) {
    if (playlistToLoad != null) {
      callback.onPlaylistRefreshRequired(playlistToLoad);
    }
    return false;
  }
  if (isMediaChunk(loadable)) {
    pendingResetPositionUs=C.TIME_UNSET;
    HlsMediaChunk mediaChunk=(HlsMediaChunk)loadable;
    mediaChunk.init(this);
    mediaChunks.add(mediaChunk);
    upstreamTrackFormat=mediaChunk.trackFormat;
  }
  long elapsedRealtimeMs=loader.startLoading(loadable,this,loadErrorHandlingPolicy.getMinimumLoadableRetryCount(loadable.type));
  eventDispatcher.loadStarted(loadable.dataSpec,loadable.type,trackType,loadable.trackFormat,loadable.trackSelectionReason,loadable.trackSelectionData,loadable.startTimeUs,loadable.endTimeUs,elapsedRealtimeMs);
  return true;
}
