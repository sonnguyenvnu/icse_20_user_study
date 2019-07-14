@Override public KeyColumnValueStore openDatabase(String longName,StoreMetaData.Container metaData) throws BackendException {
  Preconditions.checkArgument(!storageConfig.has(GraphDatabaseConfiguration.STORE_META_TTL,longName) || !storageConfig.get(GraphDatabaseConfiguration.STORE_META_TTL,longName));
  HBaseKeyColumnValueStore store=openStores.get(longName);
  if (store == null) {
    final String cfName=getCfNameForStoreName(longName);
    HBaseKeyColumnValueStore newStore=new HBaseKeyColumnValueStore(this,cnx,tableName,cfName,longName);
    store=openStores.putIfAbsent(longName,newStore);
    if (store == null) {
      if (!skipSchemaCheck) {
        int cfTTLInSeconds=-1;
        if (metaData.contains(StoreMetaData.TTL)) {
          cfTTLInSeconds=metaData.get(StoreMetaData.TTL);
        }
        ensureColumnFamilyExists(tableName,cfName,cfTTLInSeconds);
      }
      store=newStore;
    }
  }
  return store;
}
