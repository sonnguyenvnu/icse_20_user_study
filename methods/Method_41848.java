/** 
 * <p> Resume (un-pause) all of the <code> {@link org.quartz.JobDetail}s</code> in the given group. </p> <p> If any of the <code>Job</code> s had <code>Trigger</code> s that missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 */
@Override public Collection<String> resumeJobs(GroupMatcher<JobKey> matcher) throws JobPersistenceException {
  Collection<String> groups=new HashSet<String>();
  lock();
  try {
    Set<JobKey> jobKeys=getJobKeys(matcher);
    for (    JobKey jobKey : jobKeys) {
      if (groups.add(jobKey.getGroup())) {
        jobFacade.removePausedJobGroup(jobKey.getGroup());
      }
      for (      OperableTrigger trigger : getTriggersForJob(jobKey)) {
        resumeTrigger(trigger.getKey());
      }
    }
  }
  finally {
    unlock();
  }
  return groups;
}
