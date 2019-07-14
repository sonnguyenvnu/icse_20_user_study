@Override public boolean unDeployMachineCollection(long hostId,String ip){
  Assert.isTrue(hostId > 0);
  Assert.hasText(ip);
  TriggerKey collectionTriggerKey=TriggerKey.triggerKey(ip,ConstUtils.MACHINE_TRIGGER_GROUP + hostId);
  Trigger trigger=schedulerCenter.getTrigger(collectionTriggerKey);
  if (trigger == null) {
    return true;
  }
  return schedulerCenter.unscheduleJob(collectionTriggerKey);
}
