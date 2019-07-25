@Override public SimpleLookupCache<K,V> snapshot(){
  return new SimpleLookupCache<K,V>(_initialEntries,_maxEntries);
}
