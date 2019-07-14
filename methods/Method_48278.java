@Override public void clearStorage() throws BackendException {
  for (  InMemoryKeyColumnValueStore store : stores.values()) {
    store.clear();
  }
  stores.clear();
}
