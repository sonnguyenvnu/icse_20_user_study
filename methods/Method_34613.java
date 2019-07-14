/** 
 * Whether the <code>run()</code> resulted in a failure (exception).
 * @return boolean
 */
public boolean isFailedExecution(){
  return getCommandResult().getEventCounts().contains(HystrixEventType.FAILURE);
}
