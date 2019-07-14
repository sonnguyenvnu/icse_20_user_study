/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public void resumeJobs(GroupMatcher<JobKey> matcher) throws SchedulerException {
  String operation=null;
switch (matcher.getCompareWithOperator()) {
case EQUALS:
    operation="resumeJobGroup";
  break;
case STARTS_WITH:
operation="resumeJobsStartingWith";
break;
case ENDS_WITH:
operation="resumeJobsEndingWith";
break;
case CONTAINS:
operation="resumeJobsContaining";
case ANYTHING:
operation="resumeJobsAll";
}
invoke(operation,new Object[]{matcher.getCompareToValue()},new String[]{String.class.getName()});
}
