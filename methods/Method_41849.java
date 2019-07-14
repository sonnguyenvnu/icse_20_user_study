/** 
 * <p> Pause all triggers - equivalent of calling <code>pauseTriggerGroup(group)</code> on every group. </p> <p> When <code>resumeAll()</code> is called (to un-pause), trigger misfire instructions WILL be applied. </p>
 * @see #resumeAll()
 * @see #pauseTriggers(org.quartz.impl.matchers.GroupMatcher)
 */
@Override public void pauseAll() throws JobPersistenceException {
  lock();
  try {
    List<String> names=getTriggerGroupNames();
    for (    String name : names) {
      pauseTriggers(GroupMatcher.triggerGroupEquals(name));
    }
  }
  finally {
    unlock();
  }
}
