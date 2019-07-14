@Override public String executeCommand(AppDesc appDesc,String command){
  if (AppDescEnum.AppTest.NOT_TEST.getValue() == appDesc.getIsTest()) {
    if (!RedisReadOnlyCommandEnum.contains(command)) {
      return "online app only support read-only and safe command";
    }
  }
  int type=appDesc.getType();
  long appId=appDesc.getAppId();
  String password=appDesc.getPassword();
  if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
    JedisSentinelPool jedisSentinelPool=getJedisSentinelPool(appDesc);
    if (jedisSentinelPool == null) {
      return "sentinel can not execute ";
    }
    Jedis jedis=null;
    try {
      jedis=jedisSentinelPool.getResource();
      String host=jedis.getClient().getHost();
      int port=jedis.getClient().getPort();
      return executeCommand(appId,host,port,command);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
      return "????:" + e.getMessage();
    }
 finally {
      if (jedis != null)       jedis.close();
      jedisSentinelPool.destroy();
    }
  }
 else   if (type == ConstUtils.CACHE_REDIS_STANDALONE) {
    List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
    if (instanceList == null || instanceList.isEmpty()) {
      return "?????????";
    }
    String host=null;
    int port=0;
    for (    InstanceInfo instanceInfo : instanceList) {
      host=instanceInfo.getIp();
      port=instanceInfo.getPort();
      break;
    }
    try {
      return executeCommand(appId,host,port,command);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
      return "????:" + e.getMessage();
    }
  }
 else   if (type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER) {
    List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
    if (instanceList == null || instanceList.isEmpty()) {
      return "?????????";
    }
    Set<HostAndPort> clusterHosts=new LinkedHashSet<HostAndPort>();
    for (    InstanceInfo instance : instanceList) {
      if (instance == null || instance.getStatus() == InstanceStatusEnum.OFFLINE_STATUS.getStatus()) {
        continue;
      }
      clusterHosts.add(new HostAndPort(instance.getIp(),instance.getPort()));
    }
    if (clusterHosts.isEmpty()) {
      return "no run instance";
    }
    String commandKey=getCommandKey(command);
    for (    HostAndPort hostAndPort : clusterHosts) {
      HostAndPort rightHostAndPort=getClusterRightHostAndPort(hostAndPort.getHost(),hostAndPort.getPort(),password,command,commandKey);
      if (rightHostAndPort != null) {
        try {
          return executeCommand(appId,rightHostAndPort.getHost(),rightHostAndPort.getPort(),command);
        }
 catch (        Exception e) {
          logger.error(e.getMessage(),e);
          return "????:" + e.getMessage();
        }
      }
    }
  }
  return "???????";
}
