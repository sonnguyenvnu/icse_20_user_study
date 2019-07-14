public Id fetchServerThreadPoolActiveCountId(ServerConfig serverConfig){
  String key="rpc." + serverConfig.getProtocol() + ".threadpool.active.count";
  return fetchServerConfigId(key);
}
