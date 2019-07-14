/** 
 * <p> Pause all of the <code> {@link Trigger}s</code> in the given group. </p> <p> The JobStore should "remember" that the group is paused, and impose the pause on any new triggers that are added to the group while the group is paused. </p>
 */
@Override public Collection<String> pauseTriggers(GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
  HashSet<String> pausedGroups=new HashSet<String>();
  lock();
  try {
    Set<TriggerKey> triggerKeys=getTriggerKeys(matcher);
    for (    TriggerKey key : triggerKeys) {
      triggerFacade.addPausedGroup(key.getGroup());
      pausedGroups.add(key.getGroup());
      pauseTrigger(key);
    }
    StringMatcher.StringOperatorName operator=matcher.getCompareWithOperator();
    if (operator.equals(StringOperatorName.EQUALS)) {
      triggerFacade.addPausedGroup(matcher.getCompareToValue());
      pausedGroups.add(matcher.getCompareToValue());
    }
  }
  finally {
    unlock();
  }
  return pausedGroups;
}
