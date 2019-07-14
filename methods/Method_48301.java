final KVQuery convertQuery(final KeySliceQuery query){
  Predicate<StaticBuffer> filter=Predicates.alwaysTrue();
  if (!hasFixedKeyLength()) {
    filter=keyAndColumn -> equalKey(keyAndColumn,query.getKey());
  }
  return new KVQuery(concatenatePrefix(query.getKey(),query.getSliceStart()),concatenatePrefix(query.getKey(),query.getSliceEnd()),filter,query.getLimit());
}
