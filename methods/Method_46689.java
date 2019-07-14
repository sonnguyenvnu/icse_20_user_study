@Override public void deleteTransactionInfo(String groupId,String unitId,String modId) throws TxManagerException {
  List<String> remoteKeys=rpcClient.remoteKeys(modId);
  if (remoteKeys.isEmpty()) {
    throw new TxManagerException("??????");
  }
  try {
    for (    String remoteKey : remoteKeys) {
      MessageDto messageDto=rpcClient.request(remoteKey,MessageCreator.deleteAspectLog(groupId,unitId),5000);
      if (MessageUtils.statusOk(messageDto)) {
        return;
      }
    }
  }
 catch (  RpcException e) {
    throw new TxManagerException(e.getMessage());
  }
}
