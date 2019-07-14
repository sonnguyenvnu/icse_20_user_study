@Override public boolean unDeployRedisSlowLogCollection(long appId,String host,int port){
  Assert.isTrue(appId > 0);
  Assert.hasText(host);
  Assert.isTrue(port > 0);
  TriggerKey triggerKey=TriggerKey.triggerKey(ObjectConvert.linkIpAndPort(host,port),ConstUtils.REDIS_SLOWLOG_TRIGGER_GROUP + appId);
  Trigger trigger=schedulerCenter.getTrigger(triggerKey);
  if (trigger == null) {
    return true;
  }
  return schedulerCenter.unscheduleJob(triggerKey);
}
