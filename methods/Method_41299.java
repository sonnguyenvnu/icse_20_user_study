/** 
 * <p> Resume (un-pause) all of the <code> {@link org.quartz.Trigger}s</code> matching the given groupMatcher. </p> <p> If any <code>Trigger</code> missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 * @see #pauseTriggers(org.quartz.impl.matchers.GroupMatcher)
 */
public Set<String> resumeTriggerGroup(Connection conn,GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
  try {
    getDelegate().deletePausedTriggerGroup(conn,matcher);
    HashSet<String> groups=new HashSet<String>();
    Set<TriggerKey> keys=getDelegate().selectTriggersInGroup(conn,matcher);
    for (    TriggerKey key : keys) {
      resumeTrigger(conn,key);
      groups.add(key.getGroup());
    }
    return groups;
  }
 catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't pause trigger group '" + matcher + "': " + e.getMessage(),e);
  }
}
