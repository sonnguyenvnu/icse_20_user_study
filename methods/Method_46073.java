public Id fetchServerThreadPoolIdleCountId(ServerConfig serverConfig){
  String key="rpc." + serverConfig.getProtocol() + ".threadpool.idle.count";
  return fetchServerConfigId(key);
}
