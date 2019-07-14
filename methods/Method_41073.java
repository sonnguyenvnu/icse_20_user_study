public void pauseTriggersEndingWith(String triggerGroupSuffix) throws Exception {
  pauseTriggers(GroupMatcher.<TriggerKey>groupEndsWith(triggerGroupSuffix));
}
