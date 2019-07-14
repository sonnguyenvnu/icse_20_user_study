public void resumeTriggerGroup(String triggerGroup) throws Exception {
  resumeTriggers(GroupMatcher.<TriggerKey>groupEquals(triggerGroup));
}
