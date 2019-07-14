@Override public boolean hasLoadedRelations(final SliceQuery query){
synchronized (queryCache) {
    return queryCache.get(query) != null || getSuperResultSet(query) != null;
  }
}
