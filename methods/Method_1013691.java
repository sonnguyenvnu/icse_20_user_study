@Override public CrossDcLeaderAwareHealthCheckAction create(RedisHealthCheckInstance instance){
  return new RedisMasterCheckAction(scheduled,instance,executors,redisService);
}
