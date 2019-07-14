@Override public StoreTransaction openTx() throws BackendException {
  StandardBaseTransactionConfig config;
  if (keyConsistentOperations) {
    config=StandardBaseTransactionConfig.of(times,manager.storeManager.getFeatures().getKeyConsistentTxConfig());
  }
 else {
    config=StandardBaseTransactionConfig.of(times);
  }
  return manager.storeManager.beginTransaction(config);
}
