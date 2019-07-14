/** 
 * Whether the response received was the result of a timeout and <code>getFallback()</code> being called.
 * @return boolean
 */
public boolean isResponseTimedOut(){
  return getCommandResult().getEventCounts().contains(HystrixEventType.TIMEOUT);
}
