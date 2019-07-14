@Override public void onLoadCanceled(Chunk loadable,long elapsedRealtimeMs,long loadDurationMs,boolean released){
  eventDispatcher.loadCanceled(loadable.dataSpec,loadable.getUri(),loadable.getResponseHeaders(),loadable.type,trackType,loadable.trackFormat,loadable.trackSelectionReason,loadable.trackSelectionData,loadable.startTimeUs,loadable.endTimeUs,elapsedRealtimeMs,loadDurationMs,loadable.bytesLoaded());
  if (!released) {
    resetSampleQueues();
    if (enabledTrackGroupCount > 0) {
      callback.onContinueLoadingRequested(this);
    }
  }
}
