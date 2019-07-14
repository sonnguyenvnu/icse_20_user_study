/** 
 * List of HystrixCommandEventType enums representing events that occurred during execution. <p> Examples of events are SUCCESS, FAILURE, TIMEOUT, and SHORT_CIRCUITED
 * @return {@code List<HystrixEventType>}
 */
public List<HystrixEventType> getExecutionEvents(){
  return getCommandResult().getOrderedList();
}
