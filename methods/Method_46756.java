private MessageDto request0(RpcCmd rpcCmd,long timeout) throws RpcException {
  if (rpcCmd.getKey() == null) {
    throw new RpcException("key must be not null.");
  }
  return SocketManager.getInstance().request(rpcCmd.getRemoteKey(),rpcCmd,timeout);
}
