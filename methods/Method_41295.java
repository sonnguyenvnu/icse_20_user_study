/** 
 * <p> Resume (un-pause) all of the <code> {@link org.quartz.Job}s</code> in the given group. </p> <p> If any of the <code>Job</code> s had <code>Trigger</code> s that missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 * @see #pauseJobs(org.quartz.impl.matchers.GroupMatcher)
 */
@SuppressWarnings("unchecked") public Set<String> resumeJobs(final GroupMatcher<JobKey> matcher) throws JobPersistenceException {
  return (Set<String>)executeInLock(LOCK_TRIGGER_ACCESS,new TransactionCallback(){
    public Set<String> execute(    Connection conn) throws JobPersistenceException {
      Set<JobKey> jobKeys=getJobNames(conn,matcher);
      Set<String> groupNames=new HashSet<String>();
      for (      JobKey jobKey : jobKeys) {
        List<OperableTrigger> triggers=getTriggersForJob(conn,jobKey);
        for (        OperableTrigger trigger : triggers) {
          resumeTrigger(conn,trigger.getKey());
        }
        groupNames.add(jobKey.getGroup());
      }
      return groupNames;
    }
  }
);
}
