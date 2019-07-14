@Override public int getLeftRequestsCount(Task task){
  Jedis jedis=pool.getResource();
  try {
    Long size=jedis.llen(getQueueKey(task));
    return size.intValue();
  }
  finally {
    pool.returnResource(jedis);
  }
}
