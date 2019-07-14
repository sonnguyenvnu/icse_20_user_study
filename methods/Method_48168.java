public EntryList edgeStoreQuery(final KeySliceQuery query){
  return executeRead(new Callable<EntryList>(){
    @Override public EntryList call() throws Exception {
      return cacheEnabled ? edgeStore.getSlice(query,storeTx) : edgeStore.getSliceNoCache(query,storeTx);
    }
    @Override public String toString(){
      return "EdgeStoreQuery";
    }
  }
);
}
