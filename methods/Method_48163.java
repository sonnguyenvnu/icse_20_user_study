public ExternalCachePersistor getTxLogPersistor(){
  return new ExternalCachePersistor(txLogStore,storeTx);
}
