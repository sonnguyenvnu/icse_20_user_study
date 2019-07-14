public void setRpcConfig(RpcConfig rpcConfig){
  cacheSize=rpcConfig.getCacheSize();
  waitTime=rpcConfig.getWaitTime() == -1 ? 500 : rpcConfig.getWaitTime();
}
