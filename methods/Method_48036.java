@Override public BerkeleyJETx beginTransaction(final BaseTransactionConfig txCfg) throws BackendException {
  try {
    Transaction tx=null;
    Configuration effectiveCfg=new MergedConfiguration(txCfg.getCustomOptions(),getStorageConfig());
    if (transactional) {
      TransactionConfig txnConfig=new TransactionConfig();
      ConfigOption.getEnumValue(effectiveCfg.get(ISOLATION_LEVEL),IsolationLevel.class).configure(txnConfig);
      tx=environment.beginTransaction(null,txnConfig);
    }
    BerkeleyJETx btx=new BerkeleyJETx(tx,ConfigOption.getEnumValue(effectiveCfg.get(LOCK_MODE),LockMode.class),txCfg);
    if (log.isTraceEnabled()) {
      log.trace("Berkeley tx created",new TransactionBegin(btx.toString()));
    }
    return btx;
  }
 catch (  DatabaseException e) {
    throw new PermanentBackendException("Could not start BerkeleyJE transaction",e);
  }
}
