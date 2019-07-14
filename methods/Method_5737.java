@Override public CacheDataSource createDataSource(){
  return new CacheDataSource(cache,upstreamFactory.createDataSource(),cacheReadDataSourceFactory.createDataSource(),cacheWriteDataSinkFactory == null ? null : cacheWriteDataSinkFactory.createDataSink(),flags,eventListener,cacheKeyFactory);
}
