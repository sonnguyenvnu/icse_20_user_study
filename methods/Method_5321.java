@Override public void onLoadCanceled(ExtractingLoadable loadable,long elapsedRealtimeMs,long loadDurationMs,boolean released){
  eventDispatcher.loadCanceled(loadable.dataSpec,loadable.dataSource.getLastOpenedUri(),loadable.dataSource.getLastResponseHeaders(),C.DATA_TYPE_MEDIA,C.TRACK_TYPE_UNKNOWN,null,C.SELECTION_REASON_UNKNOWN,null,loadable.seekTimeUs,durationUs,elapsedRealtimeMs,loadDurationMs,loadable.dataSource.getBytesRead());
  if (!released) {
    copyLengthFromLoader(loadable);
    for (    SampleQueue sampleQueue : sampleQueues) {
      sampleQueue.reset();
    }
    if (enabledTrackCount > 0) {
      Assertions.checkNotNull(callback).onContinueLoadingRequested(this);
    }
  }
}
