public void revert(Jedis jedis){
  pool.returnResource(jedis);
}
