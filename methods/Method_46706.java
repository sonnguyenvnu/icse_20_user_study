@Override public Serializable execute(TransactionCmd transactionCmd) throws TxManagerException {
  try {
    DTXContext dtxContext=dtxContextRegistry.get(transactionCmd.getGroupId());
    NotifyGroupParams notifyGroupParams=transactionCmd.getMsg().loadBean(NotifyGroupParams.class);
    int commitState=notifyGroupParams.getState();
    int transactionState=transactionManager.transactionStateFromFastStorage(transactionCmd.getGroupId());
    if (transactionState == 0) {
      commitState=0;
    }
    txLogger.txTrace(transactionCmd.getGroupId(),"","notify group state: {}",notifyGroupParams.getState());
    if (commitState == 1) {
      transactionManager.commit(dtxContext);
    }
 else     if (commitState == 0) {
      transactionManager.rollback(dtxContext);
    }
    if (transactionState == 0) {
      txLogger.txTrace(transactionCmd.getGroupId(),"","mandatory rollback for user.");
    }
    return commitState;
  }
 catch (  TransactionException e) {
    throw new TxManagerException(e);
  }
 finally {
    transactionManager.close(transactionCmd.getGroupId());
    txLogger.txTrace(transactionCmd.getGroupId(),"","notify group successfully.");
  }
}
