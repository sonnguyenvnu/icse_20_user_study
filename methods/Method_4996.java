/** 
 * Returns a new  {@link CacheDataSource} instance which accesses cache read-only and throws anexception on cache miss.
 */
public CacheDataSource createOfflineCacheDataSource(){
  return offlineCacheDataSourceFactory.createDataSource();
}
