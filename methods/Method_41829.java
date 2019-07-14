/** 
 * <p> Retrieve the <code> {@link org.quartz.JobDetail}</code> for the given <code> {@link org.quartz.Job}</code>. </p>
 * @param jobKey The key of the <code>Job</code> to be retrieved.
 * @return The desired <code>Job</code>, or null if there is no match.
 */
@Override public JobDetail retrieveJob(JobKey jobKey) throws JobPersistenceException {
  JobWrapper jobWrapper=getJob(jobKey);
  return jobWrapper == null ? null : (JobDetail)jobWrapper.getJobDetailClone();
}
