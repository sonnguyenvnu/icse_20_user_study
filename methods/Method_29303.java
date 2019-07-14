/** 
 * ??meet
 * @param masterHostAndPostList
 * @param host
 * @param port
 * @return
 */
private boolean clusterMeet(long appId,List<HostAndPort> masterHostAndPostList,final String host,final int port){
  boolean isSingleNode=redisCenter.isSingleClusterNode(appId,host,port);
  if (!isSingleNode) {
    logger.error("{}:{} isNotSingleNode",host,port);
    return false;
  }
 else {
    logger.warn("{}:{} isSingleNode",host,port);
  }
  for (  HostAndPort hostAndPort : masterHostAndPostList) {
    String clusterHost=hostAndPort.getHost();
    int clusterPort=hostAndPort.getPort();
    final Jedis jedis=redisCenter.getJedis(appId,clusterHost,clusterPort,defaultTimeout,defaultTimeout);
    try {
      boolean isClusterMeet=new IdempotentConfirmer(){
        @Override public boolean execute(){
          String meet=jedis.clusterMeet(host,port);
          return meet != null && meet.equalsIgnoreCase("OK");
        }
      }
.run();
      if (isClusterMeet) {
        return true;
      }
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
 finally {
      if (jedis != null)       jedis.close();
    }
  }
  return false;
}
