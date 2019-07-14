/** 
 * Performs disk cache check synchronously. It is not recommended to use this unless you know what exactly you are doing. Disk cache check is a costly operation, the call will block the caller thread until the cache check is completed.
 * @param imageRequest the imageRequest for the image to be looked up.
 * @return true if the image was found in the disk cache, false otherwise.
 */
public boolean isInDiskCacheSync(final ImageRequest imageRequest){
  final CacheKey cacheKey=mCacheKeyFactory.getEncodedCacheKey(imageRequest,null);
  final ImageRequest.CacheChoice cacheChoice=imageRequest.getCacheChoice();
switch (cacheChoice) {
case DEFAULT:
    return mMainBufferedDiskCache.diskCheckSync(cacheKey);
case SMALL:
  return mSmallImageBufferedDiskCache.diskCheckSync(cacheKey);
default :
return false;
}
}
