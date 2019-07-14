/** 
 * <p> Resume (un-pause) all of the <code> {@link Trigger}s</code> in the given group. </p> <p> If any <code>Trigger</code> missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 */
@Override public Collection<String> resumeTriggers(GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
  Collection<String> groups=new HashSet<String>();
  lock();
  try {
    Set<TriggerKey> triggerKeys=getTriggerKeys(matcher);
    for (    TriggerKey triggerKey : triggerKeys) {
      TriggerWrapper tw=triggerFacade.get(triggerKey);
      if (tw != null) {
        String jobGroup=tw.getJobKey().getGroup();
        if (jobFacade.pausedGroupsContain(jobGroup)) {
          continue;
        }
        groups.add(triggerKey.getGroup());
      }
      resumeTrigger(triggerKey);
    }
    triggerFacade.removeAllPausedGroups(groups);
  }
  finally {
    unlock();
  }
  return groups;
}
