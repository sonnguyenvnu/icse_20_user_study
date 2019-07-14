@Override public Serializable execute(TransactionCmd transactionCmd) throws TxManagerException {
  try {
    TxExceptionParams txExceptionParams=transactionCmd.getMsg().loadBean(TxExceptionParams.class);
    WriteTxExceptionDTO writeTxExceptionReq=new WriteTxExceptionDTO();
    writeTxExceptionReq.setModId(rpcClient.getAppName(transactionCmd.getRemoteKey()));
    int transactionState=transactionManager.transactionStateFromFastStorage(transactionCmd.getGroupId());
    writeTxExceptionReq.setTransactionState(transactionState == -1 ? txExceptionParams.getTransactionState() : transactionState);
    writeTxExceptionReq.setGroupId(txExceptionParams.getGroupId());
    writeTxExceptionReq.setUnitId(txExceptionParams.getUnitId());
    writeTxExceptionReq.setRegistrar(Objects.isNull(txExceptionParams.getRegistrar()) ? -1 : txExceptionParams.getRegistrar());
    writeTxExceptionReq.setRemark(txExceptionParams.getRemark());
    compensationService.writeTxException(writeTxExceptionReq);
  }
 catch (  Exception e) {
    throw new TxManagerException(e);
  }
  return null;
}
