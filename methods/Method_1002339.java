public Xid[] recover(int flag) throws XAException {
  this.checkParticipantReadyIfNecessary();
  TransactionRepository repository=beanFactory.getTransactionRepository();
  List<Transaction> transactionList=repository.getActiveTransactionList();
  TransactionXid[] xidArray=new TransactionXid[transactionList.size()];
  for (int i=0; i < transactionList.size(); i++) {
    Transaction transaction=transactionList.get(i);
    xidArray[i]=transaction.getTransactionContext().getXid();
  }
  return xidArray;
}
