private String getConfigValue(long appId,String host,int port,String key){
  Jedis jedis=redisCenter.getJedis(appId,host,port,Protocol.DEFAULT_TIMEOUT * 3,Protocol.DEFAULT_TIMEOUT * 3);
  try {
    List<String> values=jedis.configGet(key);
    if (values == null || values.size() < 1) {
      return null;
    }
    return values.get(1);
  }
 catch (  Exception e) {
    throw new RuntimeException(e.getMessage(),e);
  }
 finally {
    jedis.close();
  }
}
