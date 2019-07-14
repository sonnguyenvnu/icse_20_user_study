@Override public EntryList getSlice(KeySliceQuery query,StoreTransaction txh) throws BackendException {
  ColumnValueStore cvs=kcv.get(query.getKey());
  if (cvs == null)   return EntryList.EMPTY_LIST;
 else   return cvs.getSlice(query,txh);
}
