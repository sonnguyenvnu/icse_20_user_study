/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
@SuppressWarnings("unchecked") public Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher) throws SchedulerException {
  if (matcher.getCompareWithOperator().equals(StringMatcher.StringOperatorName.EQUALS)) {
    List<JobKey> keys=(List<JobKey>)invoke("getJobNames",new Object[]{matcher.getCompareToValue()},new String[]{String.class.getName()});
    return new HashSet<JobKey>(keys);
  }
 else {
    throw new SchedulerException("Only equals matcher are supported for looking up JobKeys");
  }
}
