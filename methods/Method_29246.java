/** 
 * ??ip?port??redis??????????
 * @param ip   ip
 * @param port port
 * @return ???true????false?
 */
public Boolean hasSlaves(long appId,String ip,int port){
  Jedis jedis=getJedis(appId,ip,port,REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
  try {
    String info=jedis.info("all");
    Map<RedisConstant,Map<String,Object>> infoMap=processRedisStats(info);
    return hasSlaves(infoMap);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return null;
  }
 finally {
    jedis.close();
  }
}
