/** 
 * <p> Set whether or not the the <code>Scheduler</code> should re-execute the <code>Job</code> if a 'recovery' or 'fail-over' situation is encountered. </p> <p> If not explicitly set, the default value is <code>false</code>. </p>
 * @see JobExecutionContext#isRecovering()
 */
public void setRequestsRecovery(boolean shouldRecover){
  this.shouldRecover=shouldRecover;
}
