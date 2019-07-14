public RpcContent loadRpcContent(){
  if (rpcContent == null) {
    rpcContent=RpcCmdContext.getInstance().getKey(getKey());
  }
  return rpcContent;
}
