@Override public boolean psync(RedisClient redisClient,String[] args) throws Exception {
  throw new NoMasterlinkRedisError("keeper state :" + keeperState());
}
