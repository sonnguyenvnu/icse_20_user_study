/** 
 * Returns a copy of this CacheSpan whose last access time stamp is set to current time. This doesn't copy or change the underlying cache file.
 * @param id The cache file id.
 * @return A {@link SimpleCacheSpan} with updated last access time stamp.
 * @throws IllegalStateException If called on a non-cached span (i.e. {@link #isCached} is false).
 */
public SimpleCacheSpan copyWithUpdatedLastAccessTime(int id){
  Assertions.checkState(isCached);
  long now=System.currentTimeMillis();
  File newCacheFile=getCacheFile(file.getParentFile(),id,position,now);
  return new SimpleCacheSpan(key,position,length,now,newCacheFile);
}
