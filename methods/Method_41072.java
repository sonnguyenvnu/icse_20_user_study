public void pauseTriggersStartingWith(String triggerGroupPrefix) throws Exception {
  pauseTriggers(GroupMatcher.<TriggerKey>groupStartsWith(triggerGroupPrefix));
}
