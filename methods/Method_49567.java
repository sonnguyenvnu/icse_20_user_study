@Override public Map<StaticBuffer,EntryList> getSlice(List<StaticBuffer> keys,SliceQuery query,StoreTransaction txh) throws BackendException {
  return getHelper(keys,getFilter(query));
}
