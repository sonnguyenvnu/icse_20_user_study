public void take(StoreTransaction tx,KeyColumn kc,S ls){
  getLocksForTx(tx).put(kc,ls);
}
