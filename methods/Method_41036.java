/** 
 * <p> Pause all of the <code> {@link Trigger}s</code> in the matching groups. </p>
 */
public void pauseTriggers(GroupMatcher<TriggerKey> matcher) throws SchedulerException {
  validateState();
  if (matcher == null) {
    matcher=GroupMatcher.groupEquals(Scheduler.DEFAULT_GROUP);
  }
  Collection<String> pausedGroups=resources.getJobStore().pauseTriggers(matcher);
  notifySchedulerThread(0L);
  for (  String pausedGroup : pausedGroups) {
    notifySchedulerListenersPausedTriggers(pausedGroup);
  }
}
