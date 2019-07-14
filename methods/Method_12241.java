/** 
 * <p> ???key???????field??value?????????? </p>
 * @param key
 * @param field
 * @param value
 * @return
 */
public Long hincrby(String key,String field,Long value){
  Jedis jedis=null;
  Long res=null;
  try {
    jedis=pool.getResource();
    res=jedis.hincrBy(key,field,value);
  }
 catch (  Exception e) {
    LOGGER.error(e.getMessage());
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
