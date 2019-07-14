@Override public int transactionStateFromFastStorage(String groupId){
  return dtxContextRegistry.transactionState(groupId);
}
