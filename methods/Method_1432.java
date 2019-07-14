/** 
 * Returns whether the image is stored in the bitmap memory cache.
 * @param imageRequest the imageRequest for the image to be looked up.
 * @return true if the image was found in the bitmap memory cache, false otherwise.
 */
public boolean isInBitmapMemoryCache(final ImageRequest imageRequest){
  if (imageRequest == null) {
    return false;
  }
  final CacheKey cacheKey=mCacheKeyFactory.getBitmapCacheKey(imageRequest,null);
  CloseableReference<CloseableImage> ref=mBitmapMemoryCache.get(cacheKey);
  try {
    return CloseableReference.isValid(ref);
  }
  finally {
    CloseableReference.closeSafely(ref);
  }
}
