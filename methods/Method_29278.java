private boolean clusterMeet(Jedis jedis,long appId,String host,int port){
  boolean isSingleNode=redisCenter.isSingleClusterNode(appId,host,port);
  if (!isSingleNode) {
    logger.error("{}:{} isNotSingleNode",host,port);
    return false;
  }
 else {
    logger.warn("{}:{} isSingleNode",host,port);
  }
  String response=jedis.clusterMeet(host,port);
  boolean isMeet=response != null && response.equalsIgnoreCase("OK");
  if (!isMeet) {
    logger.error("{}:{} meet error",host,port);
    return false;
  }
  return true;
}
