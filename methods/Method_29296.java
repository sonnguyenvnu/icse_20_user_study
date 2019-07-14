@Override public boolean addSlave(long appId,int instanceId,final String slaveHost){
  Assert.isTrue(appId > 0);
  Assert.isTrue(instanceId > 0);
  Assert.isTrue(StringUtils.isNotBlank(slaveHost));
  AppDesc appDesc=appDao.getAppDescById(appId);
  Assert.isTrue(appDesc != null);
  int type=appDesc.getType();
  if (!TypeUtil.isRedisType(type)) {
    logger.error("{} is not redis type",appDesc);
    return false;
  }
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  Assert.isTrue(instanceInfo != null);
  String masterHost=instanceInfo.getIp();
  int masterPort=instanceInfo.getPort();
  final Integer slavePort=machineCenter.getAvailablePort(slaveHost,ConstUtils.CACHE_REDIS_STANDALONE);
  if (slavePort == null) {
    logger.error("host={} getAvailablePort is null",slaveHost);
    return false;
  }
  boolean isRun;
  if (TypeUtil.isRedisCluster(type)) {
    isRun=runInstance(appDesc,slaveHost,slavePort,instanceInfo.getMem(),true);
  }
 else {
    isRun=runInstance(appDesc,slaveHost,slavePort,instanceInfo.getMem(),false);
  }
  if (!isRun) {
    logger.error("{}:{} is not run",slaveHost,slavePort);
    return false;
  }
  boolean isCopy=copyCommonConfig(appId,masterHost,masterPort,slaveHost,slavePort);
  if (!isCopy) {
    logger.error("{}:{} copy config {}:{} is error",masterHost,masterPort,slaveHost,slavePort);
    return false;
  }
  if (TypeUtil.isRedisCluster(type)) {
    final Jedis masterJedis=redisCenter.getJedis(appId,masterHost,masterPort,Protocol.DEFAULT_TIMEOUT,Protocol.DEFAULT_TIMEOUT);
    final Jedis slaveJedis=redisCenter.getJedis(appId,slaveHost,slavePort,Protocol.DEFAULT_TIMEOUT,Protocol.DEFAULT_TIMEOUT);
    try {
      boolean isClusterMeet=clusterMeet(masterJedis,appId,slaveHost,slavePort);
      if (!isClusterMeet) {
        logger.error("{}:{} cluster is failed",slaveHost,slaveHost);
        return isClusterMeet;
      }
      final String nodeId=redisCenter.getNodeId(appId,masterHost,masterPort);
      if (StringUtils.isBlank(nodeId)) {
        logger.error("{}:{} getNodeId failed",masterHost,masterPort);
        return false;
      }
      boolean isClusterReplicate=new IdempotentConfirmer(){
        @Override public boolean execute(){
          try {
            TimeUnit.SECONDS.sleep(2);
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
          String response=slaveJedis.clusterReplicate(nodeId);
          logger.info("clusterReplicate-{}:{}={}",slaveHost,slavePort,response);
          return response != null && response.equalsIgnoreCase("OK");
        }
      }
.run();
      if (!isClusterReplicate) {
        logger.error("{}:{} clusterReplicate {} is failed ",slaveHost,slavePort,nodeId);
        return false;
      }
      masterJedis.clusterSaveConfig();
      slaveJedis.clusterSaveConfig();
      redisCenter.configRewrite(appId,masterHost,masterPort);
      redisCenter.configRewrite(appId,slaveHost,slavePort);
    }
  finally {
      masterJedis.close();
      slaveJedis.close();
    }
  }
 else {
    boolean isSlave=slaveOf(appId,masterHost,masterPort,slaveHost,slavePort);
    if (!isSlave) {
      logger.error("{}:{} sync {}:{} is error",slaveHost,slavePort,masterHost,masterPort);
      return false;
    }
  }
  if (TypeUtil.isRedisCluster(type)) {
    saveInstance(appId,slaveHost,slavePort,instanceInfo.getMem(),ConstUtils.CACHE_TYPE_REDIS_CLUSTER,"");
  }
 else {
    saveInstance(appId,slaveHost,slavePort,instanceInfo.getMem(),ConstUtils.CACHE_REDIS_STANDALONE,"");
  }
  boolean isDeploy=redisCenter.deployRedisCollection(appId,slaveHost,slavePort);
  if (!isDeploy) {
    logger.warn("host={},port={},isMasterDeploy=false",slaveHost,slavePort);
  }
  return true;
}
