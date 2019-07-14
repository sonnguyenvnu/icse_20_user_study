@Override public Map<StaticBuffer,EntryList> getSlice(final List<StaticBuffer> keys,final SliceQuery query,final StoreTransaction txh) throws BackendException {
  throw new UnsupportedOperationException("The CQL backend does not support multi-key queries");
}
