@Override public synchronized CassandraThriftKeyColumnValueStore openDatabase(final String name,StoreMetaData.Container metaData) throws BackendException {
  if (openStores.containsKey(name))   return openStores.get(name);
  ensureColumnFamilyExists(keySpaceName,name);
  CassandraThriftKeyColumnValueStore store=new CassandraThriftKeyColumnValueStore(keySpaceName,name,this,pool);
  openStores.put(name,store);
  return store;
}
