@Override @SuppressWarnings("unchecked") public void onRemoved(Iterable<CacheEntryEvent<? extends K,? extends V>> events){
  if (listener instanceof CacheEntryRemovedListener<?,?>) {
    ((CacheEntryRemovedListener<K,V>)listener).onRemoved(events);
  }
}
