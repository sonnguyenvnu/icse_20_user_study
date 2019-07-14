/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
@SuppressWarnings("unchecked") public List<String> getJobGroupNames() throws SchedulerException {
  return (List<String>)getAttribute("JobGroupNames");
}
