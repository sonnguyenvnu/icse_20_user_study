@Override public void delete(K key,@Nullable Expirable<V> expirable,RemovalCause cause){
  if (cause.wasEvicted() && (expirable != null)) {
    V value=expirable.get();
    if (cause == RemovalCause.EXPIRED) {
      dispatcher.publishExpiredQuietly(cache,key,value);
    }
 else {
      dispatcher.publishRemovedQuietly(cache,key,value);
    }
    statistics.recordEvictions(1L);
  }
}
