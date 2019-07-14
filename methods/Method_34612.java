/** 
 * Whether the response was returned successfully either by executing <code>run()</code> or from cache.
 * @return boolean
 */
public boolean isSuccessfulExecution(){
  return getCommandResult().getEventCounts().contains(HystrixEventType.SUCCESS);
}
