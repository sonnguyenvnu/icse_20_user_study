/** 
 * Instructs the <code>Scheduler</code> whether or not the <code>Job</code> should be re-executed if a 'recovery' or 'fail-over' situation is encountered. <p> If not explicitly set, the default value is <code>false</code>. - this method sets the value to <code>true</code>. </p>
 * @return the updated JobBuilder
 * @see JobDetail#requestsRecovery()
 */
public JobBuilder requestRecovery(){
  this.shouldRecover=true;
  return this;
}
