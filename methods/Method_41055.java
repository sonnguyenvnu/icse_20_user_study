/** 
 * Interrupt all instances of the identified InterruptableJob executing in  this Scheduler instance. <p> This method is not cluster aware.  That is, it will only interrupt  instances of the identified InterruptableJob currently executing in this  Scheduler instance, not across the entire cluster. </p>
 * @see org.quartz.core.RemotableQuartzScheduler#interrupt(JobKey)
 */
public boolean interrupt(JobKey jobKey) throws UnableToInterruptJobException {
  List<JobExecutionContext> jobs=getCurrentlyExecutingJobs();
  JobDetail jobDetail=null;
  Job job=null;
  boolean interrupted=false;
  for (  JobExecutionContext jec : jobs) {
    jobDetail=jec.getJobDetail();
    if (jobKey.equals(jobDetail.getKey())) {
      job=jec.getJobInstance();
      if (job instanceof InterruptableJob) {
        ((InterruptableJob)job).interrupt();
        interrupted=true;
      }
 else {
        throw new UnableToInterruptJobException("Job " + jobDetail.getKey() + " can not be interrupted, since it does not implement " + InterruptableJob.class.getName());
      }
    }
  }
  return interrupted;
}
