public void rollback() throws IllegalStateException, SecurityException, SystemException {
  TransactionManager transactionManager=this.beanFactory.getTransactionManager();
  CompensableManager compensableManager=this.beanFactory.getCompensableManager();
  Transaction transaction=transactionManager.getTransactionQuietly();
  Transaction compensable=compensableManager.getCompensableTransactionQuietly();
  TransactionContext transactionContext=null;
  if (transaction == null && compensable == null) {
    throw new IllegalStateException();
  }
 else   if (compensable == null) {
    transactionContext=transaction.getTransactionContext();
  }
 else {
    transactionContext=compensable.getTransactionContext();
  }
  if (org.bytesoft.compensable.TransactionContext.class.isInstance(transactionContext)) {
    org.bytesoft.compensable.TransactionContext compensableContext=(org.bytesoft.compensable.TransactionContext)transactionContext;
    if (compensableContext.isRecoveried()) {
      if (compensableContext.isCompensable() == false) {
        throw new IllegalStateException();
      }
      compensableManager.rollback();
    }
 else     if (compensableContext.isCompensable() == false) {
      transactionManager.rollback();
    }
 else     if (compensableContext.isCompensating()) {
      compensableManager.rollback();
    }
 else     if (compensableContext.isCoordinator()) {
      if (compensableContext.isPropagated()) {
        compensableManager.rollback();
      }
 else       if (compensableContext.getPropagationLevel() > 0) {
        compensableManager.rollback();
      }
 else {
        compensableManager.compensableRollback();
      }
    }
 else {
      compensableManager.rollback();
    }
  }
 else {
    transactionManager.rollback();
  }
}
