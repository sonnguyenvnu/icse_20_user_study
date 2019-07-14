/** 
 * Removes all images with the specified  {@link Uri} from disk cache.
 * @param imageRequest The imageRequest for the image to evict from disk cache
 */
public void evictFromDiskCache(final ImageRequest imageRequest){
  CacheKey cacheKey=mCacheKeyFactory.getEncodedCacheKey(imageRequest,null);
  mMainBufferedDiskCache.remove(cacheKey);
  mSmallImageBufferedDiskCache.remove(cacheKey);
}
