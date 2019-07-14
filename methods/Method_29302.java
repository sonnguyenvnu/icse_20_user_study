/** 
 * ??????
 */
public boolean joinCluster(long appId,String masterHost,int masterPort,final String slaveHost,final int slavePort){
  final Jedis masterJedis=redisCenter.getJedis(appId,masterHost,masterPort,defaultTimeout,defaultTimeout);
  boolean isRun=redisCenter.isRun(appId,masterHost,masterPort);
  if (!isRun) {
    logger.error(String.format("joinCluster: master host=%s,port=%s is not run",masterHost,masterPort));
    return false;
  }
  boolean hasSlave=StringUtils.isNotBlank(slaveHost) && slavePort > 0;
  final Jedis slaveJedis=hasSlave ? redisCenter.getJedis(appId,slaveHost,slavePort,defaultTimeout,defaultTimeout) : null;
  if (hasSlave) {
    isRun=redisCenter.isRun(appId,slaveHost,slavePort);
    if (!isRun) {
      logger.error(String.format("joinCluster: slave host=%s,port=%s is not run",slaveHost,slavePort));
      return false;
    }
  }
  List<HostAndPort> masterHostAndPostList=getMasterNodeList(appId);
  boolean isClusterMeet=clusterMeet(appId,masterHostAndPostList,masterHost,masterPort);
  if (!isClusterMeet) {
    logger.error("master isClusterMeet failed {}:{}",masterHost,masterPort);
    return false;
  }
  if (hasSlave) {
    isClusterMeet=clusterMeet(appId,masterHostAndPostList,slaveHost,slavePort);
    if (!isClusterMeet) {
      logger.error("slave isClusterMeet failed {}:{}",slaveHost,slavePort);
      return false;
    }
  }
  if (hasSlave) {
    final String masterNodeId=getNodeId(appId,masterJedis);
    if (masterNodeId == null) {
      logger.error(String.format("joinCluster:host=%s,port=%s nodeId is null",masterHost,masterPort));
      return false;
    }
    return new IdempotentConfirmer(){
      @Override public boolean execute(){
        try {
          TimeUnit.SECONDS.sleep(2);
        }
 catch (        Exception e) {
          logger.error(e.getMessage(),e);
        }
        String response=slaveJedis.clusterReplicate(masterNodeId);
        logger.info("clusterReplicate-{}:{}={}",slaveHost,slavePort,response);
        return response != null && response.equalsIgnoreCase("OK");
      }
    }
.run();
  }
 else {
    return true;
  }
}
