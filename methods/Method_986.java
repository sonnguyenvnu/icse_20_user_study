/** 
 * Gets the image to be reused, or null if there is no such image. <p> The returned image is the least recently used image that has no more clients referencing it, and it has not yet been evicted from the cache. <p> The client can freely modify the bitmap of the returned image and can cache it again without any restrictions.
 */
@Nullable public CloseableReference<CloseableImage> getForReuse(){
  while (true) {
    CacheKey key=popFirstFreeItemKey();
    if (key == null) {
      return null;
    }
    CloseableReference<CloseableImage> imageRef=mBackingCache.reuse(key);
    if (imageRef != null) {
      return imageRef;
    }
  }
}
