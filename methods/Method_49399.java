@Override public KeyColumnValueStore openDatabase(final String name,final Container metaData) throws BackendException {
  Supplier<Boolean> initializeTable=() -> Optional.ofNullable(this.cluster.getMetadata().getKeyspace(this.keyspace)).map(k -> k.getTable(name) == null).orElse(true);
  return this.openStores.computeIfAbsent(name,n -> new CQLKeyColumnValueStore(this,n,getStorageConfig(),() -> this.openStores.remove(n),allowCompactStorage,initializeTable));
}
