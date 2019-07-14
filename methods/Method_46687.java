@Override public JSONObject getTransactionInfo(String groupId,String unitId) throws TransactionStateException {
  TxException exception=txExceptionRepository.findByGroupIdAndUnitId(groupId,unitId);
  if (Objects.isNull(exception)) {
    throw new TransactionStateException("non exists aspect log",TransactionStateException.NON_ASPECT);
  }
  List<String> remoteKeys=rpcClient.remoteKeys(exception.getModId());
  if (remoteKeys.isEmpty()) {
    throw new TransactionStateException("non mod found",TransactionStateException.NON_MOD);
  }
  try {
    for (    String remoteKey : remoteKeys) {
      MessageDto messageDto=rpcClient.request(remoteKey,MessageCreator.getAspectLog(groupId,unitId),5000);
      if (MessageUtils.statusOk(messageDto)) {
        return messageDto.loadBean(JSONObject.class);
      }
    }
    throw new TransactionStateException("non exists aspect log",TransactionStateException.NON_ASPECT);
  }
 catch (  RpcException e) {
    throw new TransactionStateException(e,TransactionStateException.RPC_ERR);
  }
}
