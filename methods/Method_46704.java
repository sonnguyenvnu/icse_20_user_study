@Override public Serializable execute(TransactionCmd transactionCmd) throws TxManagerException {
  InitClientParams initClientParams=transactionCmd.getMsg().loadBean(InitClientParams.class);
  log.info("Registered TC: {}",initClientParams.getLabelName());
  try {
    rpcClient.bindAppName(transactionCmd.getRemoteKey(),initClientParams.getAppName(),initClientParams.getLabelName());
  }
 catch (  RpcException e) {
    throw new TxManagerException(e);
  }
  initClientParams.setSeqLen(txManagerConfig.getSeqLen());
  initClientParams.setMachineId(managerService.machineIdSync());
  initClientParams.setDtxTime(txManagerConfig.getDtxTime());
  initClientParams.setTmRpcTimeout(rpcConfig.getWaitTime());
  initClientParams.setAppName(modIdProvider.modId());
  return initClientParams;
}
