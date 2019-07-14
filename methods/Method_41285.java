/** 
 * <p> Get the names of all of the <code> {@link org.quartz.Job}</code> s that matcher the given groupMatcher. </p> <p> If there are no jobs in the given group name, the result should be an empty Set </p>
 */
@SuppressWarnings("unchecked") public Set<JobKey> getJobKeys(final GroupMatcher<JobKey> matcher) throws JobPersistenceException {
  return (Set<JobKey>)executeWithoutLock(new TransactionCallback(){
    public Object execute(    Connection conn) throws JobPersistenceException {
      return getJobNames(conn,matcher);
    }
  }
);
}
