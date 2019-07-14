private StoreTransaction overrideTimestamp(final StoreTransaction tx,final Instant commitTime) throws BackendException {
  StandardBaseTransactionConfig newCfg=new StandardBaseTransactionConfig.Builder(tx.getConfiguration()).commitTime(commitTime).build();
  return manager.beginTransaction(newCfg);
}
