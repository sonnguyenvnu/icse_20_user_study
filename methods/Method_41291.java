/** 
 * <p> Pause all of the <code> {@link org.quartz.Job}s</code> matching the given groupMatcher - by pausing all of their <code>Trigger</code>s. </p>
 * @see #resumeJobs(org.quartz.impl.matchers.GroupMatcher)
 */
@SuppressWarnings("unchecked") public Set<String> pauseJobs(final GroupMatcher<JobKey> matcher) throws JobPersistenceException {
  return (Set<String>)executeInLock(LOCK_TRIGGER_ACCESS,new TransactionCallback(){
    public Set<String> execute(    final Connection conn) throws JobPersistenceException {
      Set<String> groupNames=new HashSet<String>();
      Set<JobKey> jobNames=getJobNames(conn,matcher);
      for (      JobKey jobKey : jobNames) {
        List<OperableTrigger> triggers=getTriggersForJob(conn,jobKey);
        for (        OperableTrigger trigger : triggers) {
          pauseTrigger(conn,trigger.getKey());
        }
        groupNames.add(jobKey.getGroup());
      }
      return groupNames;
    }
  }
);
}
