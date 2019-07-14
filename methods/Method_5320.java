@Override public void onLoadCompleted(ExtractingLoadable loadable,long elapsedRealtimeMs,long loadDurationMs){
  if (durationUs == C.TIME_UNSET) {
    SeekMap seekMap=Assertions.checkNotNull(this.seekMap);
    long largestQueuedTimestampUs=getLargestQueuedTimestampUs();
    durationUs=largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + DEFAULT_LAST_SAMPLE_DURATION_US;
    listener.onSourceInfoRefreshed(durationUs,seekMap.isSeekable());
  }
  eventDispatcher.loadCompleted(loadable.dataSpec,loadable.dataSource.getLastOpenedUri(),loadable.dataSource.getLastResponseHeaders(),C.DATA_TYPE_MEDIA,C.TRACK_TYPE_UNKNOWN,null,C.SELECTION_REASON_UNKNOWN,null,loadable.seekTimeUs,durationUs,elapsedRealtimeMs,loadDurationMs,loadable.dataSource.getBytesRead());
  copyLengthFromLoader(loadable);
  loadingFinished=true;
  Assertions.checkNotNull(callback).onContinueLoadingRequested(this);
}
