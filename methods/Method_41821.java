protected OperableTrigger createRecoveryTrigger(TriggerWrapper tw,JobWrapper jw,String name,FiredTrigger recovering){
  final SimpleTriggerImpl recoveryTrigger=new SimpleTriggerImpl(name,Scheduler.DEFAULT_RECOVERY_GROUP,new Date(recovering.getScheduledFireTime()));
  recoveryTrigger.setJobName(jw.getKey().getName());
  recoveryTrigger.setJobGroup(jw.getKey().getGroup());
  recoveryTrigger.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);
  recoveryTrigger.setPriority(tw.getPriority());
  return recoveryTrigger;
}
