@Override public StoreTransaction openTx() throws BackendException {
  return manager.beginTransaction(storeTxConfigBuilder.build());
}
