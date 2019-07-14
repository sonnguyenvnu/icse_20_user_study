/** 
 * <p> Get all <code> {@link Trigger}</code> s that are associated with the identified <code> {@link org.quartz.JobDetail}</code>. </p>
 */
public List<? extends Trigger> getTriggersOfJob(JobKey jobKey) throws SchedulerException {
  validateState();
  return resources.getJobStore().getTriggersForJob(jobKey);
}
