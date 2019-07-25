public Transaction start(TransactionContext transactionContext,int flags) throws XAException {
  CompensableManager compensableManager=this.beanFactory.getCompensableManager();
  CompensableLogger compensableLogger=this.beanFactory.getCompensableLogger();
  TransactionRepository compensableRepository=this.beanFactory.getCompensableRepository();
  TransactionLock compensableLock=this.beanFactory.getCompensableLock();
  if (compensableManager.getTransactionQuietly() != null) {
    throw new XAException(XAException.XAER_PROTO);
  }
  boolean transactionContextStatefully=((org.bytesoft.compensable.TransactionContext)transactionContext).isStatefully();
  if (transactionContextStatefully != this.statefully) {
    throw new XAException(XAException.XAER_PROTO);
  }
  TransactionXid globalXid=transactionContext.getXid();
  Transaction transaction=null;
  try {
    transaction=compensableRepository.getTransaction(globalXid);
  }
 catch (  TransactionException tex) {
    throw new XAException(XAException.XAER_RMERR);
  }
  if (transaction == null) {
    transaction=new CompensableTransactionImpl((org.bytesoft.compensable.TransactionContext)transactionContext);
    ((CompensableTransactionImpl)transaction).setBeanFactory(this.beanFactory);
    compensableLogger.createTransaction(((CompensableTransactionImpl)transaction).getTransactionArchive());
    compensableRepository.putTransaction(globalXid,transaction);
    logger.info("{}| compensable transaction begin!",ByteUtils.byteArrayToString(globalXid.getGlobalTransactionId()));
  }
 else   if (transaction.getTransactionStatus() != Status.STATUS_ACTIVE) {
    throw new XAException(XAException.XAER_PROTO);
  }
  boolean locked=compensableLock.lockTransaction(globalXid,this.endpoint);
  if (locked == false) {
    throw new XAException(XAException.XAER_PROTO);
  }
  org.bytesoft.compensable.TransactionContext compensableContext=(org.bytesoft.compensable.TransactionContext)transaction.getTransactionContext();
  int propagationLevel=compensableContext.getPropagationLevel();
  compensableContext.setPropagationLevel(propagationLevel + 1);
  compensableManager.associateThread(transaction);
  return transaction;
}
