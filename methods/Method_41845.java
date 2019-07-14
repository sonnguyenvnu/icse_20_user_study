/** 
 * <p> Pause all of the <code> {@link org.quartz.JobDetail}s</code> in the given group - by pausing all of their <code>Trigger</code>s. </p> <p> The JobStore should "remember" that the group is paused, and impose the pause on any new jobs that are added to the group while the group is paused. </p>
 */
@Override public Collection<String> pauseJobs(GroupMatcher<JobKey> matcher) throws JobPersistenceException {
  Collection<String> pausedGroups=new HashSet<String>();
  lock();
  try {
    Set<JobKey> jobKeys=getJobKeys(matcher);
    for (    JobKey jobKey : jobKeys) {
      for (      OperableTrigger trigger : getTriggersForJob(jobKey)) {
        pauseTrigger(trigger.getKey());
      }
      pausedGroups.add(jobKey.getGroup());
    }
    StringMatcher.StringOperatorName operator=matcher.getCompareWithOperator();
    if (operator.equals(StringOperatorName.EQUALS)) {
      jobFacade.addPausedGroup(matcher.getCompareToValue());
      pausedGroups.add(matcher.getCompareToValue());
    }
  }
  finally {
    unlock();
  }
  return pausedGroups;
}
