/** 
 * De-duplicates keys arrays to keep the memory footprint on CompactMap to a minimum. This implementation is blocking for simplicity. To improve performance in multi-threaded environments, use a thread-local KEY_HULL and a concurrent hash map for KEY_CACHE.
 * @param keys String array to deduplicate by checking against KEY_CACHE
 * @return A de-duplicated version of the given keys array
 */
private static String[] deduplicateKeys(String[] keys){
synchronized (KEY_CACHE) {
    KEY_HULL.setKeys(keys);
    KeyContainer retrieved=KEY_CACHE.get(KEY_HULL);
    if (retrieved == null) {
      retrieved=new KeyContainer(keys);
      KEY_CACHE.put(retrieved,retrieved);
    }
    return retrieved.getKeys();
  }
}
