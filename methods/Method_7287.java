/** 
 * Get a cache of Strategies for a particular field
 * @param field The Calendar field
 * @return a cache of Locale to Strategy
 */
private static ConcurrentMap<Locale,Strategy> getCache(final int field){
synchronized (caches) {
    if (caches[field] == null) {
      caches[field]=new ConcurrentHashMap<Locale,Strategy>(3);
    }
    return caches[field];
  }
}
