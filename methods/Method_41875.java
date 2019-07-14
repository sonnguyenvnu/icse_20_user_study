@Override public void triggeredJobComplete(OperableTrigger trigger,JobDetail jobDetail,CompletedExecutionInstruction triggerInstCode){
  clusteredJobStore.triggeredJobComplete(trigger,jobDetail,triggerInstCode);
}
