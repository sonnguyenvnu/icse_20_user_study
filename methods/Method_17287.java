@Override public boolean containsKey(K key){
  requireNotClosed();
  Expirable<V> expirable=cache.getIfPresent(key);
  if (expirable == null) {
    return false;
  }
  if (!expirable.isEternal() && expirable.hasExpired(currentTimeMillis())) {
    cache.asMap().computeIfPresent(key,(k,e) -> {
      if (e == expirable) {
        dispatcher.publishExpired(this,key,expirable.get());
        statistics.recordEvictions(1);
        return null;
      }
      return e;
    }
);
    dispatcher.awaitSynchronous();
    return false;
  }
  return true;
}
