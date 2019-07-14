@Override public KeyColumnValueStore openDatabase(String name,StoreMetaData.Container metaData) throws BackendException {
  KeyColumnValueStore store=manager.openDatabase(name);
  final int storeTTL=metaData.contains(StoreMetaData.TTL) ? metaData.get(StoreMetaData.TTL) : -1;
  Preconditions.checkArgument(storeTTL > 0,"TTL must be positive: %s",storeTTL);
  ttlEnabledStores.put(name,storeTTL);
  return new TTLKCVS(store,storeTTL);
}
