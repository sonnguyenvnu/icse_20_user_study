@Override public synchronized AstyanaxKeyColumnValueStore openDatabase(String name,StoreMetaData.Container metaData) throws BackendException {
  if (openStores.containsKey(name))   return openStores.get(name);
 else {
    ensureColumnFamilyExists(name);
    AstyanaxKeyColumnValueStore store=new AstyanaxKeyColumnValueStore(name,keyspaceContext.getClient(),this,retryPolicy);
    openStores.put(name,store);
    return store;
  }
}
