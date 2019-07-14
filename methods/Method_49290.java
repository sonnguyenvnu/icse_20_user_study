private void evictEntry(Long key){
  CacheEntry<Long,V> o=map.remove(key);
  if (o == null)   return;
  stats.size.decrementAndGet();
  stats.evictionCounter.incrementAndGet();
  if (evictionListener != null)   evictionListener.evictedEntry(o.key,o.value);
}
