@Override default @Nullable V get(K key,Function<? super K,? extends V> mappingFunction){
  return cache().computeIfAbsent(key,mappingFunction);
}
