@Override public boolean deployClusterInstance(long appId,List<RedisClusterNode> clusterNodes,int maxMemory){
  if (!isExist(appId)) {
    return false;
  }
  AppDesc appDesc=appDao.getAppDescById(appId);
  String host=null;
  Integer port=null;
  Map<Jedis,Jedis> clusterMap=new LinkedHashMap<Jedis,Jedis>();
  for (  RedisClusterNode node : clusterNodes) {
    String masterHost=node.getMasterHost();
    String slaveHost=node.getSlaveHost();
    Integer masterPort=machineCenter.getAvailablePort(masterHost,ConstUtils.CACHE_TYPE_REDIS_CLUSTER);
    if (masterPort == null) {
      logger.error("masterHost={} getAvailablePort is null",masterHost);
      return false;
    }
    if (host == null || port == null) {
      host=masterHost;
      port=masterPort;
    }
    boolean isMasterRun=runInstance(appDesc,masterHost,masterPort,maxMemory,true);
    if (!isMasterRun) {
      return false;
    }
    if (StringUtils.isNotBlank(slaveHost)) {
      Integer slavePort=machineCenter.getAvailablePort(slaveHost,ConstUtils.CACHE_TYPE_REDIS_CLUSTER);
      if (slavePort == null) {
        logger.error("slavePort={} getAvailablePort is null",slavePort);
        return false;
      }
      boolean isSlaveRun=runInstance(appDesc,slaveHost,slavePort,maxMemory,true);
      if (!isSlaveRun) {
        return false;
      }
      clusterMap.put(redisCenter.getJedis(appId,masterHost,masterPort),redisCenter.getJedis(appId,slaveHost,slavePort));
    }
 else {
      clusterMap.put(redisCenter.getJedis(appId,masterHost,masterPort),null);
    }
  }
  boolean isCluster;
  try {
    isCluster=startCluster(appId,clusterMap);
    if (!isCluster) {
      logger.error("startCluster create error!");
      return false;
    }
    for (    Map.Entry<Jedis,Jedis> entry : clusterMap.entrySet()) {
      Jedis master=entry.getKey();
      Jedis slave=entry.getValue();
      saveInstance(appId,master.getClient().getHost(),master.getClient().getPort(),maxMemory,ConstUtils.CACHE_TYPE_REDIS_CLUSTER,"");
      redisCenter.deployRedisCollection(appId,master.getClient().getHost(),master.getClient().getPort());
      if (slave != null) {
        saveInstance(appId,slave.getClient().getHost(),slave.getClient().getPort(),maxMemory,ConstUtils.CACHE_TYPE_REDIS_CLUSTER,"");
        redisCenter.deployRedisCollection(appId,slave.getClient().getHost(),slave.getClient().getPort());
      }
    }
  }
  finally {
    for (    Jedis master : clusterMap.keySet()) {
      master.close();
      if (clusterMap.get(master) != null) {
        clusterMap.get(master).close();
      }
    }
  }
  return true;
}
