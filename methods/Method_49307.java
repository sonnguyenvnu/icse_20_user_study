private Map.Entry<SliceQuery,EntryList> getSuperResultSet(final SliceQuery query){
synchronized (queryCache) {
    if (queryCache.size() > 0) {
      for (      Map.Entry<SliceQuery,EntryList> entry : queryCache.entrySet()) {
        if (entry.getKey().subsumes(query))         return entry;
      }
    }
  }
  return null;
}
