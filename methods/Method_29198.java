@Override public boolean inspect(Map<InspectParamEnum,Object> paramMap){
  final String host=MapUtils.getString(paramMap,InspectParamEnum.SPLIT_KEY);
  List<InstanceInfo> list=(List<InstanceInfo>)paramMap.get(InspectParamEnum.INSTANCE_LIST);
  outer:   for (  InstanceInfo info : list) {
    final int port=info.getPort();
    final int type=info.getType();
    final long appId=info.getAppId();
    int status=info.getStatus();
    if (status != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
      continue;
    }
    if (TypeUtil.isRedisDataType(type)) {
      Jedis jedis=redisCenter.getJedis(appId,host,port,REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
      try {
        Map<String,String> persistenceMap=parseMap(jedis);
        if (persistenceMap.isEmpty()) {
          logger.error("{}:{} get persistenceMap failed",host,port);
          continue;
        }
        if (!isAofEnabled(persistenceMap)) {
          continue;
        }
        long aofCurrentSize=MapUtils.getLongValue(persistenceMap,RedisInfoEnum.aof_current_size.getValue());
        long aofBaseSize=MapUtils.getLongValue(persistenceMap,RedisInfoEnum.aof_base_size.getValue());
        long aofThresholdSize=(long)(aofBaseSize * 1.6);
        double percentage=getPercentage(aofCurrentSize,aofBaseSize);
        if (aofCurrentSize >= aofThresholdSize && aofCurrentSize > (64 * 1024 * 1024)) {
          boolean isInvoke=invokeBgRewriteAof(jedis);
          if (!isInvoke) {
            logger.error("{}:{} invokeBgRewriteAof failed",host,port);
            continue;
          }
 else {
            logger.warn("{}:{} invokeBgRewriteAof started percentage={}",host,port,percentage);
          }
          while (true) {
            try {
              TimeUnit.SECONDS.sleep(1);
              Map<String,String> loopMap=parseMap(jedis);
              Integer aofRewriteInProgress=MapUtils.getInteger(loopMap,"aof_rewrite_in_progress",null);
              if (aofRewriteInProgress == null) {
                logger.error("loop watch:{}:{} return failed",host,port);
                break;
              }
 else               if (aofRewriteInProgress <= 0) {
                logger.warn("{}:{} bgrewriteaof Done lastSize:{}Mb,currentSize:{}Mb",host,port,getMb(aofCurrentSize),getMb(MapUtils.getLongValue(loopMap,"aof_current_size")));
                break;
              }
 else {
                TimeUnit.SECONDS.sleep(1);
              }
            }
 catch (            Exception e) {
              logger.error(e.getMessage(),e);
            }
          }
        }
 else {
          if (percentage > 50D) {
            long currentSize=getMb(aofCurrentSize);
            logger.info("checked {}:{} aof increase percentage:{}% currentSize:{}Mb",host,port,percentage,currentSize > 0 ? currentSize : "<1");
          }
        }
      }
  finally {
        jedis.close();
      }
    }
  }
  return true;
}
