/** 
 * <p> ???key????????field??value </p>
 * @param key
 * @return
 */
public Map<String,String> hgetall(String key){
  Jedis jedis=null;
  Map<String,String> res=null;
  try {
    jedis=pool.getResource();
    res=jedis.hgetAll(key);
  }
 catch (  Exception e) {
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
