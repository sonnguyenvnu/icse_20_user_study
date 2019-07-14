/** 
 * <p> ???key?????????key????value </p>
 * @param key
 * @return
 */
public List<String> hvals(String key){
  Jedis jedis=null;
  List<String> res=null;
  try {
    jedis=pool.getResource();
    res=jedis.hvals(key);
  }
 catch (  Exception e) {
    LOGGER.error(e.getMessage());
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
