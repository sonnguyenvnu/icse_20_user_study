@Override public ExpectedValueCheckingTransaction beginTransaction(BaseTransactionConfig configuration) throws BackendException {
  StoreTransaction inconsistentTx=manager.beginTransaction(configuration);
  Configuration customOptions=new MergedConfiguration(storeFeatures.getKeyConsistentTxConfig(),configuration.getCustomOptions());
  BaseTransactionConfig consistentTxCfg=new StandardBaseTransactionConfig.Builder(configuration).customOptions(customOptions).build();
  StoreTransaction strongConsistentTx=manager.beginTransaction(consistentTxCfg);
  return new ExpectedValueCheckingTransaction(inconsistentTx,strongConsistentTx,maxReadTime);
}
