/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public void pauseJobs(GroupMatcher<JobKey> matcher) throws SchedulerException {
  String operation=null;
switch (matcher.getCompareWithOperator()) {
case EQUALS:
    operation="pauseJobGroup";
  break;
case STARTS_WITH:
operation="pauseJobsStartingWith";
break;
case ENDS_WITH:
operation="pauseJobsEndingWith";
break;
case CONTAINS:
operation="pauseJobsContaining";
case ANYTHING:
operation="pauseJobsAll";
}
invoke(operation,new Object[]{matcher.getCompareToValue()},new String[]{String.class.getName()});
}
