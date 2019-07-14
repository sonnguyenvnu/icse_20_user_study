private List<RedisSlowLog> getRedisSlowLogs(long appId,String host,int port,int maxCount){
  Jedis jedis=null;
  try {
    jedis=getJedis(appId,host,port,REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
    List<RedisSlowLog> resultList=new ArrayList<RedisSlowLog>();
    List<Slowlog> slowlogs=null;
    if (maxCount > 0) {
      slowlogs=jedis.slowlogGet(maxCount);
    }
 else {
      slowlogs=jedis.slowlogGet();
    }
    if (slowlogs != null && slowlogs.size() > 0) {
      for (      Slowlog sl : slowlogs) {
        RedisSlowLog rs=new RedisSlowLog();
        rs.setId(sl.getId());
        rs.setExecutionTime(sl.getExecutionTime());
        long time=sl.getTimeStamp() * 1000L;
        rs.setDate(new Date(time));
        rs.setTimeStamp(DateUtil.formatYYYYMMddHHMMSS(new Date(time)));
        rs.setCommand(StringUtils.join(sl.getArgs()," "));
        resultList.add(rs);
      }
    }
    return resultList;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
 finally {
    if (jedis != null) {
      jedis.close();
    }
  }
}
