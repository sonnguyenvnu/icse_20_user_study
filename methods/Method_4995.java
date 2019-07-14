/** 
 * Returns a new  {@link CacheDataSource} instance. 
 */
public CacheDataSource createCacheDataSource(){
  return onlineCacheDataSourceFactory.createDataSource();
}
