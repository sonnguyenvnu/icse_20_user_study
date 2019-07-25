@Override public Class<? extends CrossDcLeaderAwareHealthCheckAction> support(){
  return RedisMasterCheckAction.class;
}
