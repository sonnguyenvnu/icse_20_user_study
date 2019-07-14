/** 
 * Get a list of possible resourceIds from MultiCacheKey or get single resourceId from CacheKey.
 */
public static List<String> getResourceIds(final CacheKey key){
  try {
    final List<String> ids;
    if (key instanceof MultiCacheKey) {
      List<CacheKey> keys=((MultiCacheKey)key).getCacheKeys();
      ids=new ArrayList<>(keys.size());
      for (int i=0; i < keys.size(); i++) {
        ids.add(secureHashKey(keys.get(i)));
      }
    }
 else {
      ids=new ArrayList<>(1);
      ids.add(secureHashKey(key));
    }
    return ids;
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
