@Override public boolean continueLoading(long positionUs){
  if (loadingFinished || loader.isLoading()) {
    return false;
  }
  DataSource dataSource=dataSourceFactory.createDataSource();
  if (transferListener != null) {
    dataSource.addTransferListener(transferListener);
  }
  long elapsedRealtimeMs=loader.startLoading(new SourceLoadable(dataSpec,dataSource),this,loadErrorHandlingPolicy.getMinimumLoadableRetryCount(C.DATA_TYPE_MEDIA));
  eventDispatcher.loadStarted(dataSpec,C.DATA_TYPE_MEDIA,C.TRACK_TYPE_UNKNOWN,format,C.SELECTION_REASON_UNKNOWN,null,0,durationUs,elapsedRealtimeMs);
  return true;
}
