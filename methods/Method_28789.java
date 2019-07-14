public void renewSlotCache(Jedis jedis){
  try {
    cache.discoverClusterSlots(jedis);
  }
 catch (  JedisConnectionException e) {
    renewSlotCache();
  }
}
