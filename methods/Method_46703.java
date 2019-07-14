@Override public Serializable execute(TransactionCmd transactionCmd){
  int state=transactionManager.transactionState(transactionCmd.getGroupId());
  return state == -1 ? 0 : state;
}
