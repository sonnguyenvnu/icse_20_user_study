@Override public List<InstanceSlowLog> collectRedisSlowLog(long appId,long collectTime,String host,int port){
  Assert.isTrue(appId > 0);
  Assert.hasText(host);
  Assert.isTrue(port > 0);
  InstanceInfo instanceInfo=instanceDao.getInstByIpAndPort(host,port);
  if (instanceInfo == null) {
    return null;
  }
  if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
    return null;
  }
  List<RedisSlowLog> redisLowLogList=getRedisSlowLogs(appId,host,port,100);
  if (CollectionUtils.isEmpty(redisLowLogList)) {
    return Collections.emptyList();
  }
  final List<InstanceSlowLog> instanceSlowLogList=new ArrayList<InstanceSlowLog>();
  for (  RedisSlowLog redisSlowLog : redisLowLogList) {
    InstanceSlowLog instanceSlowLog=transferRedisSlowLogToInstance(redisSlowLog,instanceInfo);
    if (instanceSlowLog == null) {
      continue;
    }
    instanceSlowLogList.add(instanceSlowLog);
  }
  if (CollectionUtils.isEmpty(instanceSlowLogList)) {
    return Collections.emptyList();
  }
  String key=getThreadPoolKey() + "_" + host + "_" + port;
  boolean isOk=asyncService.submitFuture(getThreadPoolKey(),new KeyCallable<Boolean>(key){
    @Override public Boolean execute(){
      try {
        instanceSlowLogDao.batchSave(instanceSlowLogList);
        return true;
      }
 catch (      Exception e) {
        logger.error(e.getMessage(),e);
        return false;
      }
    }
  }
);
  if (!isOk) {
    logger.error("slowlog submitFuture failed,appId:{},collectTime:{},host:{},port:{}",appId,collectTime,host,port);
  }
  return instanceSlowLogList;
}
