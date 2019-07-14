@Override protected final StoreTransaction unwrapTx(StoreTransaction txh){
  assert txh instanceof CacheTransaction;
  return ((CacheTransaction)txh).getWrappedTransaction();
}
