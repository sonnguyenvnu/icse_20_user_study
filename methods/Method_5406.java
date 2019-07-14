@Override public LoadErrorAction onLoadError(ParsingLoadable<HlsPlaylist> loadable,long elapsedRealtimeMs,long loadDurationMs,IOException error,int errorCount){
  long retryDelayMs=loadErrorHandlingPolicy.getRetryDelayMsFor(loadable.type,loadDurationMs,error,errorCount);
  boolean isFatal=retryDelayMs == C.TIME_UNSET;
  eventDispatcher.loadError(loadable.dataSpec,loadable.getUri(),loadable.getResponseHeaders(),C.DATA_TYPE_MANIFEST,elapsedRealtimeMs,loadDurationMs,loadable.bytesLoaded(),error,isFatal);
  return isFatal ? Loader.DONT_RETRY_FATAL : Loader.createRetryAction(false,retryDelayMs);
}
