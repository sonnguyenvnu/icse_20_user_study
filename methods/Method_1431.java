/** 
 * Returns whether the image is stored in the bitmap memory cache.
 * @param uri the uri for the image to be looked up.
 * @return true if the image was found in the bitmap memory cache, false otherwise
 */
public boolean isInBitmapMemoryCache(final Uri uri){
  if (uri == null) {
    return false;
  }
  Predicate<CacheKey> bitmapCachePredicate=predicateForUri(uri);
  return mBitmapMemoryCache.contains(bitmapCachePredicate);
}
