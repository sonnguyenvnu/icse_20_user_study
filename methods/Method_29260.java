@Override public List<Integer> getClusterLossSlots(long appId,String host,int port){
  InstanceInfo instanceInfo=instanceDao.getAllInstByIpAndPort(host,port);
  if (instanceInfo == null) {
    logger.warn("{}:{} instanceInfo is null",host,port);
    return Collections.emptyList();
  }
  if (!TypeUtil.isRedisCluster(instanceInfo.getType())) {
    logger.warn("{}:{} is not rediscluster type",host,port);
    return Collections.emptyList();
  }
  List<Integer> clusterLossSlots=new ArrayList<Integer>();
  Jedis jedis=null;
  try {
    jedis=getJedis(appId,host,port,REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
    String clusterNodes=jedis.clusterNodes();
    if (StringUtils.isBlank(clusterNodes)) {
      throw new RuntimeException(host + ":" + port + "clusterNodes is null");
    }
    Set<Integer> allSlots=new LinkedHashSet<Integer>();
    for (int i=0; i <= 16383; i++) {
      allSlots.add(i);
    }
    ClusterNodeInformationParser nodeInfoParser=new ClusterNodeInformationParser();
    for (    String nodeInfo : clusterNodes.split("\n")) {
      if (StringUtils.isNotBlank(nodeInfo) && !nodeInfo.contains("disconnected")) {
        ClusterNodeInformation clusterNodeInfo=nodeInfoParser.parse(nodeInfo,new HostAndPort(host,port));
        List<Integer> availableSlots=clusterNodeInfo.getAvailableSlots();
        for (        Integer slot : availableSlots) {
          allSlots.remove(slot);
        }
      }
    }
    clusterLossSlots=new ArrayList<Integer>(allSlots);
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
