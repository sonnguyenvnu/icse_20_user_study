/** 
 * Clear (delete!) all scheduling data - all  {@link Job}s,  {@link Trigger}s {@link Calendar}s.
 * @throws JobPersistenceException
 */
public void clearAllSchedulingData() throws JobPersistenceException {
synchronized (lock) {
    List<String> lst=getTriggerGroupNames();
    for (    String group : lst) {
      Set<TriggerKey> keys=getTriggerKeys(GroupMatcher.triggerGroupEquals(group));
      for (      TriggerKey key : keys) {
        removeTrigger(key);
      }
    }
    lst=getJobGroupNames();
    for (    String group : lst) {
      Set<JobKey> keys=getJobKeys(GroupMatcher.jobGroupEquals(group));
      for (      JobKey key : keys) {
        removeJob(key);
      }
    }
    lst=getCalendarNames();
    for (    String name : lst) {
      removeCalendar(name);
    }
  }
}
