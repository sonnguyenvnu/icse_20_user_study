/** 
 * <p> Get the <code> {@link JobDetail}</code> for the <code>Job</code> instance with the given name and group. </p>
 */
public JobDetail getJobDetail(JobKey jobKey) throws SchedulerException {
  validateState();
  return resources.getJobStore().retrieveJob(jobKey);
}
