public KeyIterator edgeStoreKeys(final KeyRangeQuery range){
  Preconditions.checkArgument(storeFeatures.hasOrderedScan(),"The configured storage backend does not support ordered scans");
  return executeRead(new Callable<KeyIterator>(){
    @Override public KeyIterator call() throws Exception {
      return edgeStore.getKeys(range,storeTx);
    }
    @Override public String toString(){
      return "EdgeStoreKeys";
    }
  }
);
}
