public String getTriggerState(String triggerName,String triggerGroupName) throws Exception {
  try {
    TriggerKey triggerKey=triggerKey(triggerName,triggerGroupName);
    TriggerState ts=scheduler.getTriggerState(triggerKey);
    return ts.name();
  }
 catch (  Exception e) {
    throw newPlainException(e);
  }
}
