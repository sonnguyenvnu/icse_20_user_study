public void resumeTriggersEndingWith(String triggerGroupSuffix) throws Exception {
  resumeTriggers(GroupMatcher.<TriggerKey>groupEndsWith(triggerGroupSuffix));
}
