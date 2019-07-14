public KeyIterator edgeStoreKeys(final SliceQuery sliceQuery){
  if (!storeFeatures.hasScan())   throw new UnsupportedOperationException("The configured storage backend does not support global graph operations - use Faunus instead");
  return executeRead(new Callable<KeyIterator>(){
    @Override public KeyIterator call() throws Exception {
      return (storeFeatures.isKeyOrdered()) ? edgeStore.getKeys(new KeyRangeQuery(EDGESTORE_MIN_KEY,EDGESTORE_MAX_KEY,sliceQuery),storeTx) : edgeStore.getKeys(sliceQuery,storeTx);
    }
    @Override public String toString(){
      return "EdgeStoreKeys";
    }
  }
);
}
