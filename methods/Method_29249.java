@Override public Long getRedisMaxMemory(final long appId,final String ip,final int port){
  final String key="maxmemory";
  final Map<String,Long> resultMap=new HashMap<String,Long>();
  boolean isSuccess=new IdempotentConfirmer(){
    @Override public boolean execute(){
      Jedis jedis=null;
      try {
        jedis=getJedis(appId,ip,port);
        jedis.getClient().setConnectionTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
        jedis.getClient().setSoTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
        List<String> maxMemoryList=jedis.configGet(key);
        if (maxMemoryList != null && maxMemoryList.size() >= 2) {
          resultMap.put(key,Long.valueOf(maxMemoryList.get(1)));
        }
        return MapUtils.isNotEmpty(resultMap);
      }
 catch (      Exception e) {
        logger.warn("{}:{} errorMsg: {}",ip,port,e.getMessage());
        return false;
      }
 finally {
        if (jedis != null) {
          jedis.close();
        }
      }
    }
  }
.run();
  if (isSuccess) {
    return MapUtils.getLong(resultMap,key);
  }
 else {
    logger.error("{}:{} getMaxMemory failed!",ip,port);
    return null;
  }
}
