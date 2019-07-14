/** 
 * Instructs the <code>Scheduler</code> whether or not the <code>Job</code> should be re-executed if a 'recovery' or 'fail-over' situation is encountered. <p> If not explicitly set, the default value is <code>false</code>. </p>
 * @param jobShouldRecover the desired setting
 * @return the updated JobBuilder
 */
public JobBuilder requestRecovery(boolean jobShouldRecover){
  this.shouldRecover=jobShouldRecover;
  return this;
}
