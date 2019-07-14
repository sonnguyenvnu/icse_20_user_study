public EntryList indexQuery(final KeySliceQuery query){
  return executeRead(new Callable<EntryList>(){
    @Override public EntryList call() throws Exception {
      return cacheEnabled ? indexStore.getSlice(query,storeTx) : indexStore.getSliceNoCache(query,storeTx);
    }
    @Override public String toString(){
      return "VertexIndexQuery";
    }
  }
);
}
