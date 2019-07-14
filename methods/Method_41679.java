/** 
 * Set the identity of the Job which should be fired by the produced  Trigger, by extracting the JobKey from the given job.
 * @param jobDetail the Job to fire.
 * @return the updated TriggerBuilder
 * @see Trigger#getJobKey()
 */
public TriggerBuilder<T> forJob(JobDetail jobDetail){
  JobKey k=jobDetail.getKey();
  if (k.getName() == null)   throw new IllegalArgumentException("The given job has not yet had a name assigned to it.");
  this.jobKey=k;
  return this;
}
