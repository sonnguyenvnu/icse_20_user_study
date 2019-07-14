/** 
 * Adds the given key to the index if it isn't there already.
 * @param key The cache key that uniquely identifies the original stream.
 * @return A new or existing CachedContent instance with the given key.
 */
public CachedContent getOrAdd(String key){
  CachedContent cachedContent=keyToContent.get(key);
  return cachedContent == null ? addNew(key) : cachedContent;
}
