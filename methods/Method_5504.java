@Override public void onLoadCanceled(ParsingLoadable<SsManifest> loadable,long elapsedRealtimeMs,long loadDurationMs,boolean released){
  manifestEventDispatcher.loadCanceled(loadable.dataSpec,loadable.getUri(),loadable.getResponseHeaders(),loadable.type,elapsedRealtimeMs,loadDurationMs,loadable.bytesLoaded());
}
