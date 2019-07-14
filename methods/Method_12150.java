/** 
 * ??jedis
 * @return jedis
 */
public Jedis getResource(){
  Jedis jedis=null;
  try {
    jedis=pool.getResource();
  }
 catch (  Exception e) {
    LOG.info("can't get the redis resource");
  }
  return jedis;
}
