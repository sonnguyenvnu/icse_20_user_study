/** 
 * <p> ???key???????score??zset???value </p>
 * @param key
 * @param max
 * @param min
 * @return
 */
public Set<String> zrangebyscore(String key,String max,String min){
  Jedis jedis=null;
  Set<String> res=null;
  try {
    jedis=pool.getResource();
    res=jedis.zrevrangeByScore(key,max,min);
  }
 catch (  Exception e) {
    LOGGER.error(e.getMessage());
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
