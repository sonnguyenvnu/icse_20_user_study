/** 
 * <p> Retrieve the <code> {@link org.quartz.JobDetail}</code> for the given <code> {@link org.quartz.Job}</code>. </p>
 * @return The desired <code>Job</code>, or null if there is no match.
 */
public JobDetail retrieveJob(JobKey jobKey){
synchronized (lock) {
    JobWrapper jw=jobsByKey.get(jobKey);
    return (jw != null) ? (JobDetail)jw.jobDetail.clone() : null;
  }
}
