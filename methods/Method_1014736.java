public <T>T execute(RedisCallback<T> action){
  ShardedJedis jedis=fetchJedis();
  try {
    return action.doInRedis(jedis);
  }
  finally {
    release(jedis);
  }
}
