@Override public int getTotalRequestsCount(Task task){
  Jedis jedis=pool.getResource();
  try {
    Long size=jedis.scard(getSetKey(task));
    return size.intValue();
  }
  finally {
    pool.returnResource(jedis);
  }
}
