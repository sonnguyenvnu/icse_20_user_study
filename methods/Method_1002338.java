public Transaction end(TransactionContext transactionContext,int flags) throws XAException {
  CompensableManager compensableManager=this.beanFactory.getCompensableManager();
  TransactionLock compensableLock=this.beanFactory.getCompensableLock();
  CompensableTransaction transaction=compensableManager.getCompensableTransactionQuietly();
  if (transaction == null) {
    throw new XAException(XAException.XAER_PROTO);
  }
  compensableManager.desociateThread();
  org.bytesoft.compensable.TransactionContext compensableContext=(org.bytesoft.compensable.TransactionContext)transaction.getTransactionContext();
  int propagationLevel=compensableContext.getPropagationLevel();
  compensableContext.setPropagationLevel(propagationLevel - 1);
  compensableLock.unlockTransaction(transactionContext.getXid(),this.endpoint);
  return transaction;
}
