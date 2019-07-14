/** 
 * <p> Pause all of the <code> {@link org.quartz.JobDetail}s</code> in the given group - by pausing all of their <code>Trigger</code>s. </p> <p> The JobStore should "remember" that the group is paused, and impose the pause on any new jobs that are added to the group while the group is paused. </p>
 */
public List<String> pauseJobs(GroupMatcher<JobKey> matcher){
  List<String> pausedGroups=new LinkedList<String>();
synchronized (lock) {
    StringMatcher.StringOperatorName operator=matcher.getCompareWithOperator();
switch (operator) {
case EQUALS:
      if (pausedJobGroups.add(matcher.getCompareToValue())) {
        pausedGroups.add(matcher.getCompareToValue());
      }
    break;
default :
  for (  String group : jobsByGroup.keySet()) {
    if (operator.evaluate(group,matcher.getCompareToValue())) {
      if (pausedJobGroups.add(group)) {
        pausedGroups.add(group);
      }
    }
  }
}
for (String groupName : pausedGroups) {
for (JobKey jobKey : getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
  List<OperableTrigger> triggersOfJob=getTriggersForJob(jobKey);
  for (  OperableTrigger trigger : triggersOfJob) {
    pauseTrigger(trigger.getKey());
  }
}
}
}
return pausedGroups;
}
