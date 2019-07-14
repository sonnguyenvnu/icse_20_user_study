/** 
 * <p> Pause all of the <code> {@link org.quartz.Trigger}s</code> matching the given groupMatcher. </p>
 * @see #resumeTriggerGroup(java.sql.Connection,org.quartz.impl.matchers.GroupMatcher)
 */
@SuppressWarnings("unchecked") public Set<String> pauseTriggers(final GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
  return (Set<String>)executeInLock(LOCK_TRIGGER_ACCESS,new TransactionCallback(){
    public Set<String> execute(    Connection conn) throws JobPersistenceException {
      return pauseTriggerGroup(conn,matcher);
    }
  }
);
}
