public String randomKey(){
  String key=RandomUtils.randomKey();
  if (RpcCmdContext.getInstance().hasKey(key)) {
    return randomKey();
  }
 else {
    rpcContent=RpcCmdContext.getInstance().addKey(key);
  }
  return key;
}
