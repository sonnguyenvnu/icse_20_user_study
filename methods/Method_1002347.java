public Transaction suspend() throws SystemException {
  TransactionManager transactionManager=this.beanFactory.getTransactionManager();
  CompensableManager compensableManager=this.beanFactory.getCompensableManager();
  TransactionContext transactionContext=null;
  Transaction transaction=transactionManager.getTransactionQuietly();
  Transaction compensable=compensableManager.getCompensableTransactionQuietly();
  if (transaction == null && compensable == null) {
    throw new SystemException(XAException.XAER_NOTA);
  }
 else   if (compensable == null) {
    transactionContext=transaction.getTransactionContext();
  }
 else {
    transactionContext=compensable.getTransactionContext();
  }
  boolean isCompensableTransaction=false;
  if (org.bytesoft.compensable.TransactionContext.class.isInstance(transactionContext)) {
    org.bytesoft.compensable.TransactionContext compensableContext=(org.bytesoft.compensable.TransactionContext)transactionContext;
    isCompensableTransaction=compensableContext.isCompensable();
  }
  return (isCompensableTransaction ? compensableManager : transactionManager).suspend();
}
