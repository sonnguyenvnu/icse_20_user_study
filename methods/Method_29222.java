@Override public boolean unDeployServerCollection(long hostId,String ip){
  Assert.hasText(ip);
  TriggerKey collectionTriggerKey=TriggerKey.triggerKey(ip,ConstUtils.SERVER_TRIGGER_GROUP + ip);
  Trigger trigger=schedulerCenter.getTrigger(collectionTriggerKey);
  if (trigger == null) {
    return true;
  }
  return schedulerCenter.unscheduleJob(collectionTriggerKey);
}
