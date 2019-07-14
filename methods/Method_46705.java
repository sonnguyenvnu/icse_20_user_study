@Override public Serializable execute(TransactionCmd transactionCmd) throws TxManagerException {
  try {
    DTXContext dtxContext=dtxContextRegistry.get(transactionCmd.getGroupId());
    JoinGroupParams joinGroupParams=transactionCmd.getMsg().loadBean(JoinGroupParams.class);
    txLogger.txTrace(transactionCmd.getGroupId(),joinGroupParams.getUnitId(),"unit:{} try join group:{}",joinGroupParams.getUnitId(),transactionCmd.getGroupId());
    transactionManager.join(dtxContext,joinGroupParams.getUnitId(),joinGroupParams.getUnitType(),rpcClient.getAppName(transactionCmd.getRemoteKey()),joinGroupParams.getTransactionState());
    txLogger.txTrace(transactionCmd.getGroupId(),joinGroupParams.getUnitId(),"unit:{} joined.",joinGroupParams.getUnitId());
  }
 catch (  TransactionException e) {
    txLogger.error(this.getClass().getSimpleName(),e.getMessage());
    throw new TxManagerException(e.getLocalizedMessage());
  }
  return null;
}
