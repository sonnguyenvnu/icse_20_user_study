@Override public PingStats create(ProxyEndpoint endpoint){
  PingStats pingStats=new DefaultPingStats(resourceManager.getGlobalSharedScheduled(),endpoint,resourceManager.getKeyedObjectPool());
  allPingStats.add(pingStats);
  allEndpoints.add(endpoint);
  try {
    pingStats.start();
  }
 catch (  Exception e) {
    logger.error("[create]",e);
  }
  return pingStats;
}
