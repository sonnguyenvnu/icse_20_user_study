@Override public CrossDcLeaderAwareHealthCheckAction create(RedisHealthCheckInstance instance){
  return new VersionCheckAction(scheduled,instance,executors,alertManager);
}
