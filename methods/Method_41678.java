/** 
 * Set the identity of the Job which should be fired by the produced  Trigger - a <code>JobKey</code> will be produced with the given name and group.
 * @param jobName the name of the job to fire. 
 * @param jobGroup the group of the job to fire. 
 * @return the updated TriggerBuilder
 * @see Trigger#getJobKey()
 */
public TriggerBuilder<T> forJob(String jobName,String jobGroup){
  this.jobKey=new JobKey(jobName,jobGroup);
  return this;
}
