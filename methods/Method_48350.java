public boolean has(StoreTransaction tx,KeyColumn kc){
  return getLocksForTx(tx).containsKey(kc);
}
