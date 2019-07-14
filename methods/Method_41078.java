public void resumeTriggersStartingWith(String triggerGroupPrefix) throws Exception {
  resumeTriggers(GroupMatcher.<TriggerKey>groupStartsWith(triggerGroupPrefix));
}
