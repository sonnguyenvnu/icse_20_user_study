/** 
 * Shuts  {@link ImagePipelineFactory} down. 
 */
public static synchronized void shutDown(){
  if (sInstance != null) {
    sInstance.getBitmapMemoryCache().removeAll(AndroidPredicates.<CacheKey>True());
    sInstance.getEncodedMemoryCache().removeAll(AndroidPredicates.<CacheKey>True());
    sInstance=null;
  }
}
