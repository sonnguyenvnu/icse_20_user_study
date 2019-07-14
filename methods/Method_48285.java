@Override public Map<StaticBuffer,EntryList> getSlice(List<StaticBuffer> keys,SliceQuery query,StoreTransaction txh) throws BackendException {
  return store.getSlice(keys,query,unwrapTx(txh));
}
