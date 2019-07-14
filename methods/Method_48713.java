public EntryList edgeQuery(long vid,SliceQuery query,BackendTransaction tx){
  Preconditions.checkArgument(vid > 0);
  return tx.edgeStoreQuery(new KeySliceQuery(idManager.getKey(vid),query));
}
