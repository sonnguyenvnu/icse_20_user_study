@Override public boolean unDeployMachineMonitor(long hostId,String ip){
  Assert.isTrue(hostId > 0);
  Assert.hasText(ip);
  TriggerKey monitorTriggerKey=TriggerKey.triggerKey(ip,ConstUtils.MACHINE_MONITOR_TRIGGER_GROUP + hostId);
  Trigger trigger=schedulerCenter.getTrigger(monitorTriggerKey);
  if (trigger == null) {
    return true;
  }
  return schedulerCenter.unscheduleJob(monitorTriggerKey);
}
