@Override public void triggeredJobComplete(OperableTrigger trigger,JobDetail jobDetail,Trigger.CompletedExecutionInstruction instruction){
  realJobStore.triggeredJobComplete(trigger,jobDetail,instruction);
}
