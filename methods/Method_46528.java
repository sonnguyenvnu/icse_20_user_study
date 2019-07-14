@Override public void postBusinessCode(TxTransactionInfo info){
  int state=DTXLocalContext.transactionState(globalContext.dtxState(info.getGroupId()));
  transactionControlTemplate.notifyGroup(info.getGroupId(),info.getUnitId(),info.getTransactionType(),state);
}
