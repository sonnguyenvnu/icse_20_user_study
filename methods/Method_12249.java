/** 
 * <p> ???key?????????? </p>
 * @param key
 * @return
 */
public String type(String key){
  Jedis jedis=null;
  String res=null;
  try {
    jedis=pool.getResource();
    res=jedis.type(key);
  }
 catch (  Exception e) {
    LOGGER.error(e.getMessage());
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
