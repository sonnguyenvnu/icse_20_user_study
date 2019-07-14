/** 
 * <p> Resume (un-pause) all of the <code> {@link org.quartz.Trigger}s</code> matching the given groupMatcher. </p> <p> If any <code>Trigger</code> missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 * @see #pauseTriggers(org.quartz.impl.matchers.GroupMatcher)
 */
@SuppressWarnings("unchecked") public Set<String> resumeTriggers(final GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
  return (Set<String>)executeInLock(LOCK_TRIGGER_ACCESS,new TransactionCallback(){
    public Set<String> execute(    Connection conn) throws JobPersistenceException {
      return resumeTriggerGroup(conn,matcher);
    }
  }
);
}
