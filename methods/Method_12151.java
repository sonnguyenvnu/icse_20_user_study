/** 
 * ????jedispool???jedis?????
 * @param jedis j
 */
public void brokenResource(Jedis jedis){
  if (jedis != null) {
    try {
      pool.returnBrokenResource(jedis);
    }
 catch (    Exception e) {
      LOG.info("can't release jedis Object");
    }
  }
}
