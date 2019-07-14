/** 
 * <p> ???key??????set?????? </p>
 * @param keys ????????string ??????????string????
 * @return
 */
public Set<String> sinter(String... keys){
  Jedis jedis=null;
  Set<String> res=null;
  try {
    jedis=pool.getResource();
    res=jedis.sinter(keys);
  }
 catch (  Exception e) {
    LOGGER.error(e.getMessage());
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
