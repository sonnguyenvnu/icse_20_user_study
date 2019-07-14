/** 
 * Returns whether the image is stored in the disk cache. Performs disk cache check synchronously. It is not recommended to use this unless you know what exactly you are doing. Disk cache check is a costly operation, the call will block the caller thread until the cache check is completed.
 * @param uri the uri for the image to be looked up.
 * @return true if the image was found in the disk cache, false otherwise.
 */
public boolean isInDiskCacheSync(final Uri uri){
  return isInDiskCacheSync(uri,ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri,ImageRequest.CacheChoice.DEFAULT);
}
