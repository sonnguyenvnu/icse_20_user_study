@Override public Map<K,V> getAll(Set<? extends K> keys){
  requireNotClosed();
  boolean statsEnabled=statistics.isEnabled();
  long now=statsEnabled ? ticker.read() : 0L;
  Map<K,Expirable<V>> result=getAndFilterExpiredEntries(keys,true);
  if (statsEnabled) {
    statistics.recordGetTime(ticker.read() - now);
  }
  return copyMap(result);
}
