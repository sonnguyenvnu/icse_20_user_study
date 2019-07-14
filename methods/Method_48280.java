@Override public KeyColumnValueStore openDatabase(final String name,StoreMetaData.Container metaData) throws BackendException {
  if (!stores.containsKey(name)) {
    stores.putIfAbsent(name,new InMemoryKeyColumnValueStore(name));
  }
  KeyColumnValueStore store=stores.get(name);
  Preconditions.checkNotNull(store);
  return store;
}
