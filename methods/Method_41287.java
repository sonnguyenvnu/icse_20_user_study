/** 
 * <p> Get the names of all of the <code> {@link org.quartz.Trigger}</code> s that match the given group Matcher. </p> <p> If there are no triggers in the given group name, the result should be a an empty Set (not <code>null</code>). </p>
 */
@SuppressWarnings("unchecked") public Set<TriggerKey> getTriggerKeys(final GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {
  return (Set<TriggerKey>)executeWithoutLock(new TransactionCallback(){
    public Object execute(    Connection conn) throws JobPersistenceException {
      return getTriggerNames(conn,matcher);
    }
  }
);
}
