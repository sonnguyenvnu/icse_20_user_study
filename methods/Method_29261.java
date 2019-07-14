@Override public List<Integer> getInstanceSlots(long appId,String healthHost,int healthPort,String lossSlotsHost,int lossSlotsPort){
  InstanceInfo instanceInfo=instanceDao.getAllInstByIpAndPort(healthHost,healthPort);
  if (instanceInfo == null) {
    logger.warn("{}:{} instanceInfo is null",healthHost,healthPort);
    return Collections.emptyList();
  }
  if (!TypeUtil.isRedisCluster(instanceInfo.getType())) {
    logger.warn("{}:{} is not rediscluster type",healthHost,healthPort);
    return Collections.emptyList();
  }
  List<Integer> clusterLossSlots=new ArrayList<Integer>();
  Jedis jedis=null;
  try {
    jedis=getJedis(appId,healthHost,healthPort,REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
    String clusterNodes=jedis.clusterNodes();
    if (StringUtils.isBlank(clusterNodes)) {
      throw new RuntimeException(healthHost + ":" + healthPort + "clusterNodes is null");
    }
    ClusterNodeInformationParser nodeInfoParser=new ClusterNodeInformationParser();
    for (    String nodeInfo : clusterNodes.split("\n")) {
      if (StringUtils.isNotBlank(nodeInfo) && nodeInfo.contains("disconnected") && nodeInfo.contains(lossSlotsHost + ":" + lossSlotsPort)) {
        ClusterNodeInformation clusterNodeInfo=nodeInfoParser.parse(nodeInfo,new HostAndPort(healthHost,healthPort));
        clusterLossSlots=clusterNodeInfo.getAvailableSlots();
      }
    }
  }
 catch (  Exception e) {
    logger.error("getClusterLossSlots: " + e.getMessage(),e);
  }
 finally {
    if (jedis != null) {
      jedis.close();
    }
  }
  return clusterLossSlots;
}
