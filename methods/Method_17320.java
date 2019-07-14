@Override public Iterator<CacheEntryEvent<? extends K,? extends V>> iterator(){
  return new Iterator<CacheEntryEvent<? extends K,? extends V>>(){
    @Override public boolean hasNext(){
      return hasNext;
    }
    @Override public CacheEntryEvent<K,V> next(){
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      hasNext=false;
      return JCacheEntryEvent.this;
    }
  }
;
}
