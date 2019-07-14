/** 
 * Gets view from specified cache.
 * @param cache the cache
 * @return the first view from cache.
 */
private View getCachedView(List<View> cache){
  if (cache != null && cache.size() > 0) {
    View view=cache.get(0);
    cache.remove(0);
    return view;
  }
  return null;
}
