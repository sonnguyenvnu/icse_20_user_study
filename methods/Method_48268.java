@Override public EntryList getSlice(final KeySliceQuery query,final StoreTransaction txh) throws BackendException {
  incActionBy(1,CacheMetricsAction.RETRIEVAL,txh);
  if (isExpired(query)) {
    incActionBy(1,CacheMetricsAction.MISS,txh);
    return store.getSlice(query,unwrapTx(txh));
  }
  try {
    return cache.get(query,() -> {
      incActionBy(1,CacheMetricsAction.MISS,txh);
      return store.getSlice(query,unwrapTx(txh));
    }
);
  }
 catch (  Exception e) {
    if (e instanceof JanusGraphException)     throw (JanusGraphException)e;
 else     if (e.getCause() instanceof JanusGraphException)     throw (JanusGraphException)e.getCause();
 else     throw new JanusGraphException(e);
  }
}
