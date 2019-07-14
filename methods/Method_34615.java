/** 
 * Whether the response received was a fallback as result of being short-circuited (meaning <code>isCircuitBreakerOpen() == true</code>) and <code>getFallback()</code> being called.
 * @return boolean
 */
public boolean isResponseShortCircuited(){
  return getCommandResult().getEventCounts().contains(HystrixEventType.SHORT_CIRCUITED);
}
