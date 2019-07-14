@Override public EntryList loadRelations(final SliceQuery query,final Retriever<SliceQuery,EntryList> lookup){
  if (isNew())   return EntryList.EMPTY_LIST;
  EntryList result;
synchronized (queryCache) {
    result=queryCache.get(query);
  }
  if (result == null) {
    Map.Entry<SliceQuery,EntryList> superset=getSuperResultSet(query);
    if (superset == null || superset.getValue() == null) {
      result=lookup.get(query);
    }
 else {
      result=query.getSubset(superset.getKey(),superset.getValue());
    }
    addToQueryCache(query,result);
  }
  return result;
}
