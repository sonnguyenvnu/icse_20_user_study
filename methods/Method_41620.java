/** 
 * <p> Resume (un-pause) all of the <code> {@link org.quartz.JobDetail}s</code> in the given group. </p> <p> If any of the <code>Job</code> s had <code>Trigger</code> s that missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 */
public Collection<String> resumeJobs(GroupMatcher<JobKey> matcher){
  Set<String> resumedGroups=new HashSet<String>();
synchronized (lock) {
    Set<JobKey> keys=getJobKeys(matcher);
    for (    String pausedJobGroup : pausedJobGroups) {
      if (matcher.getCompareWithOperator().evaluate(pausedJobGroup,matcher.getCompareToValue())) {
        resumedGroups.add(pausedJobGroup);
      }
    }
    for (    String resumedGroup : resumedGroups) {
      pausedJobGroups.remove(resumedGroup);
    }
    for (    JobKey key : keys) {
      List<OperableTrigger> triggersOfJob=getTriggersForJob(key);
      for (      OperableTrigger trigger : triggersOfJob) {
        resumeTrigger(trigger.getKey());
      }
    }
  }
  return resumedGroups;
}
