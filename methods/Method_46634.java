private void report(TxExceptionParams exceptionParams){
  try {
    reliableMessenger.request(MessageCreator.writeTxException(exceptionParams));
  }
 catch (  RpcException e) {
    txLogger.trace(exceptionParams.getGroupId(),exceptionParams.getUnitId(),"TM report",REPORT_ERROR_MESSAGE);
  }
}
