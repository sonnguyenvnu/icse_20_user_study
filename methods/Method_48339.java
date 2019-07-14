protected StoreTransaction unwrapTx(StoreTransaction t){
  assert null != t;
  assert t instanceof ExpectedValueCheckingTransaction;
  return ((ExpectedValueCheckingTransaction)t).getInconsistentTx();
}
