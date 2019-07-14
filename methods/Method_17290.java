/** 
 * Performs the bulk load where the existing entries are retained. 
 */
private void loadAllAndKeepExisting(Set<? extends K> keys){
  List<K> keysToLoad=keys.stream().filter(key -> !cache.asMap().containsKey(key)).collect(toList());
  Map<K,V> result=cacheLoader.get().loadAll(keysToLoad);
  for (  Map.Entry<K,V> entry : result.entrySet()) {
    if ((entry.getKey() != null) && (entry.getValue() != null)) {
      putIfAbsentNoAwait(entry.getKey(),entry.getValue(),false);
    }
  }
}
