public Long append(String key,String value){
  ShardedJedis jedis=fetchJedis();
  try {
    return jedis.append(key,value);
  }
  finally {
    release(jedis);
  }
}
