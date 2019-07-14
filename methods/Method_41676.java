/** 
 * Set the identity of the Job which should be fired by the produced  Trigger.
 * @param keyOfJobToFire the identity of the Job to fire.
 * @return the updated TriggerBuilder
 * @see Trigger#getJobKey()
 */
public TriggerBuilder<T> forJob(JobKey keyOfJobToFire){
  this.jobKey=keyOfJobToFire;
  return this;
}
