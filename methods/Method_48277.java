@Override public StoreTransaction beginTransaction(final BaseTransactionConfig config) throws BackendException {
  return new InMemoryTransaction(config);
}
