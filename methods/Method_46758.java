@Override public MessageDto request(String remoteKey,MessageDto msg,long timeout) throws RpcException {
  long startTime=System.currentTimeMillis();
  NettyRpcCmd rpcCmd=new NettyRpcCmd();
  rpcCmd.setMsg(msg);
  String key=rpcCmd.randomKey();
  rpcCmd.setKey(key);
  rpcCmd.setRemoteKey(remoteKey);
  MessageDto result=request0(rpcCmd,timeout);
  log.debug("cmd request used time: {} ms",System.currentTimeMillis() - startTime);
  return result;
}
