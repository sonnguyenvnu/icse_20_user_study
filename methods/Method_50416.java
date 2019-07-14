/** 
 * remove guava cache by key.
 * @param key guava cache key.
 */
public void removeByKey(final String key){
  if (StringUtils.isNoneBlank(key)) {
    LOADING_CACHE.invalidate(key);
  }
}
