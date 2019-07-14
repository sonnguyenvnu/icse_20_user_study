/** 
 * <p>If you have supplied your own cache key factory when configuring the pipeline, this method may not work correctly. It will only work if the custom factory builds the cache key entirely from the URI. If that is not the case, use  {@link #evictFromDiskCache(ImageRequest)}.
 * @param uri The uri of the image to evict
 */
public void evictFromDiskCache(final Uri uri){
  evictFromDiskCache(ImageRequest.fromUri(uri));
}
