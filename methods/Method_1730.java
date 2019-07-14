/** 
 * Get the resourceId from the first key in MultiCacheKey or get single resourceId from CacheKey.
 */
public static String getFirstResourceId(final CacheKey key){
  try {
    if (key instanceof MultiCacheKey) {
      List<CacheKey> keys=((MultiCacheKey)key).getCacheKeys();
      return secureHashKey(keys.get(0));
    }
 else {
      return secureHashKey(key);
    }
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
