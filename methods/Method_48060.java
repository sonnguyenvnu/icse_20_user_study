@Override public synchronized KeyColumnValueStore openDatabase(String name,StoreMetaData.Container metaData) throws BackendException {
  if (openStores.containsKey(name))   return openStores.get(name);
  ensureKeyspaceExists(keySpaceName);
  ensureColumnFamilyExists(keySpaceName,name);
  final CassandraEmbeddedKeyColumnValueStore store=new CassandraEmbeddedKeyColumnValueStore(keySpaceName,name,this);
  openStores.put(name,store);
  return store;
}
