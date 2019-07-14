/** 
 * Removes all images with the specified  {@link Uri} from memory cache.
 * @param uri The uri of the image to evict
 */
public void evictFromMemoryCache(final Uri uri){
  Predicate<CacheKey> predicate=predicateForUri(uri);
  mBitmapMemoryCache.removeAll(predicate);
  mEncodedMemoryCache.removeAll(predicate);
}
