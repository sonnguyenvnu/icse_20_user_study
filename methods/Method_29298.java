@Override public boolean clusterFailover(final long appId,int slaveInstanceId,final String failoverParam) throws Exception {
  Assert.isTrue(appId > 0);
  Assert.isTrue(slaveInstanceId > 0);
  AppDesc appDesc=appDao.getAppDescById(appId);
  Assert.isTrue(appDesc != null);
  int type=appDesc.getType();
  if (!TypeUtil.isRedisCluster(type)) {
    logger.error("{} is not redis cluster type",appDesc);
    return false;
  }
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(slaveInstanceId);
  Assert.isTrue(instanceInfo != null);
  String slaveHost=instanceInfo.getIp();
  int slavePort=instanceInfo.getPort();
  final Jedis slaveJedis=redisCenter.getJedis(appId,slaveHost,slavePort);
  boolean isClusterFailOver=new IdempotentConfirmer(){
    @Override public boolean execute(){
      String response=null;
      if (StringUtils.isBlank(failoverParam)) {
        response=slaveJedis.clusterFailover();
      }
 else       if ("force".equals(failoverParam)) {
        response=slaveJedis.clusterFailoverForce();
      }
 else       if ("takeover".equals(failoverParam)) {
        response=slaveJedis.clusterFailoverTakeOver();
      }
 else {
        logger.error("appId {} failoverParam {} is wrong",appId,failoverParam);
      }
      return response != null && response.equalsIgnoreCase("OK");
    }
  }
.run();
  if (!isClusterFailOver) {
    logger.error("{}:{} clusterFailover {} failed",slaveHost,slavePort,failoverParam);
    return false;
  }
 else {
    logger.warn("{}:{} clusterFailover {} Done! ",slaveHost,slavePort,failoverParam);
  }
  return true;
}
