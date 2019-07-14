@Override public int transactionState(String groupId){
  int state=exceptionService.transactionState(groupId);
  if (state != -1) {
    return state;
  }
  return dtxContextRegistry.transactionState(groupId);
}
