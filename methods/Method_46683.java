@Override public boolean refresh(NotifyConnectParams notifyConnectParams) throws RpcException {
  List<String> keys=rpcClient.loadAllRemoteKey();
  if (keys != null && keys.size() > 0) {
    for (    String key : keys) {
      rpcClient.send(key,MessageCreator.newTxManager(notifyConnectParams));
    }
  }
  return true;
}
