/** 
 * Set the identity of the Job which should be fired by the produced  Trigger - a <code>JobKey</code> will be produced with the given name and default group.
 * @param jobName the name of the job (in default group) to fire. 
 * @return the updated TriggerBuilder
 * @see Trigger#getJobKey()
 */
public TriggerBuilder<T> forJob(String jobName){
  this.jobKey=new JobKey(jobName,null);
  return this;
}
