/** 
 * Performs the bulk load where the existing entries are replace. 
 */
private void loadAllAndReplaceExisting(Set<? extends K> keys){
  int[] ignored={0};
  Map<K,V> loaded=cacheLoader.get().loadAll(keys);
  for (  Map.Entry<? extends K,? extends V> entry : loaded.entrySet()) {
    putNoCopyOrAwait(entry.getKey(),entry.getValue(),false,ignored);
  }
}
