/** 
 * <p> ????????pattern??????????key </p> <p> keys(*) </p> <p> ?????????key </p>
 * @param pattern
 * @return
 */
public Set<String> keys(String pattern){
  Jedis jedis=null;
  Set<String> res=null;
  try {
    jedis=pool.getResource();
    res=jedis.keys(pattern);
  }
 catch (  Exception e) {
    LOGGER.error(e.getMessage());
  }
 finally {
    returnResource(pool,jedis);
  }
  return res;
}
