@Override public void onLoadCanceled(ParsingLoadable<HlsPlaylist> loadable,long elapsedRealtimeMs,long loadDurationMs,boolean released){
  eventDispatcher.loadCanceled(loadable.dataSpec,loadable.getUri(),loadable.getResponseHeaders(),C.DATA_TYPE_MANIFEST,elapsedRealtimeMs,loadDurationMs,loadable.bytesLoaded());
}
