public Transaction suspend() throws SystemException {
  CompensableTransaction compensable=(CompensableTransaction)this.thread2txMap.get(Thread.currentThread());
  if (compensable == null) {
    throw new SystemException(XAException.XAER_NOTA);
  }
  TransactionManager transactionManager=this.beanFactory.getTransactionManager();
  Transaction transaction=transactionManager.suspend();
  TransactionContext compensableContext=compensable.getTransactionContext();
  compensableContext.setPropagationLevel(compensableContext.getPropagationLevel() + 1);
  compensable.suspend();
  compensable.setTransactionalExtra(null);
  return transaction;
}
