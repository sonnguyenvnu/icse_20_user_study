private <T>void startLoading(ParsingLoadable<T> loadable,Loader.Callback<ParsingLoadable<T>> callback,int minRetryCount){
  long elapsedRealtimeMs=loader.startLoading(loadable,callback,minRetryCount);
  manifestEventDispatcher.loadStarted(loadable.dataSpec,loadable.type,elapsedRealtimeMs);
}
