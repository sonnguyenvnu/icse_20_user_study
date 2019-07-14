public EntryList getFromCache(final SliceQuery query){
  return queryCache.get(query);
}
