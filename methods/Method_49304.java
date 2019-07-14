protected void addToQueryCache(final SliceQuery query,final EntryList entries){
synchronized (queryCache) {
    queryCache.put(query,entries);
  }
}
