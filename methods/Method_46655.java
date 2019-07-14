private void notifyTransaction(DTXContext dtxContext,int transactionState) throws TransactionException {
  List<TransactionUnit> transactionUnits=dtxContext.transactionUnits();
  log.debug("group[{}]'s transaction units: {}",dtxContext.getGroupId(),transactionUnits);
  for (  TransactionUnit transUnit : transactionUnits) {
    NotifyUnitParams notifyUnitParams=new NotifyUnitParams();
    notifyUnitParams.setGroupId(dtxContext.getGroupId());
    notifyUnitParams.setUnitId(transUnit.getUnitId());
    notifyUnitParams.setUnitType(transUnit.getUnitType());
    notifyUnitParams.setState(transactionState);
    txLogger.txTrace(dtxContext.getGroupId(),notifyUnitParams.getUnitId(),"notify {}'s unit: {}",transUnit.getModId(),transUnit.getUnitId());
    try {
      List<String> modChannelKeys=rpcClient.remoteKeys(transUnit.getModId());
      if (modChannelKeys.isEmpty()) {
        throw new RpcException("offline mod.");
      }
      MessageDto respMsg=rpcClient.request(modChannelKeys.get(0),MessageCreator.notifyUnit(notifyUnitParams));
      if (!MessageUtils.statusOk(respMsg)) {
        List<Object> params=Arrays.asList(notifyUnitParams,transUnit.getModId());
        rpcExceptionHandler.handleNotifyUnitBusinessException(params,respMsg.loadBean(Throwable.class));
      }
    }
 catch (    RpcException e) {
      List<Object> params=Arrays.asList(notifyUnitParams,transUnit.getModId());
      rpcExceptionHandler.handleNotifyUnitMessageException(params,e);
    }
 finally {
      txLogger.txTrace(dtxContext.getGroupId(),notifyUnitParams.getUnitId(),"notify unit over");
    }
  }
}
