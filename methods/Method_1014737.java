public Double increment(String key,double delta){
  ShardedJedis jedis=fetchJedis();
  try {
    return jedis.incrByFloat(key,delta);
  }
  finally {
    release(jedis);
  }
}
