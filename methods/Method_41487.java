/** 
 * <p> This method should not be used by the Quartz client. </p> <p> Called after the <code> {@link Scheduler}</code> has executed the <code> {@link org.quartz.JobDetail}</code> associated with the <code>Trigger</code> in order to get the final instruction code from the trigger. </p>
 * @param context is the <code>JobExecutionContext</code> that was used by the <code>Job</code>'s<code>execute(xx)</code> method.
 * @param result is the <code>JobExecutionException</code> thrown by the <code>Job</code>, if any (may be null).
 * @return one of the CompletedExecutionInstruction constants.
 * @see org.quartz.Trigger.CompletedExecutionInstruction
 * @see #triggered(Calendar)
 */
public CompletedExecutionInstruction executionComplete(JobExecutionContext context,JobExecutionException result){
  if (result != null && result.refireImmediately()) {
    return CompletedExecutionInstruction.RE_EXECUTE_JOB;
  }
  if (result != null && result.unscheduleFiringTrigger()) {
    return CompletedExecutionInstruction.SET_TRIGGER_COMPLETE;
  }
  if (result != null && result.unscheduleAllTriggers()) {
    return CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_COMPLETE;
  }
  if (!mayFireAgain()) {
    return CompletedExecutionInstruction.DELETE_TRIGGER;
  }
  return CompletedExecutionInstruction.NOOP;
}
