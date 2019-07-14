@Override public StoreTransaction beginTransaction(final BaseTransactionConfig config){
  return new CassandraTransaction(config);
}
