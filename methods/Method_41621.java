/** 
 * <p> Pause all triggers - equivalent of calling <code>pauseTriggerGroup(group)</code> on every group. </p> <p> When <code>resumeAll()</code> is called (to un-pause), trigger misfire instructions WILL be applied. </p>
 * @see #resumeAll()
 * @see #pauseTrigger(org.quartz.TriggerKey)
 * @see #pauseTriggers(org.quartz.impl.matchers.GroupMatcher)
 */
public void pauseAll(){
synchronized (lock) {
    List<String> names=getTriggerGroupNames();
    for (    String name : names) {
      pauseTriggers(GroupMatcher.triggerGroupEquals(name));
    }
  }
}
