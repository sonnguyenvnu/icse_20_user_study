/** 
 * Time in Nanos when this command instance's run method was called, or -1 if not executed  for e.g., command threw an exception
 * @return long
 */
public long getCommandRunStartTimeInNanos(){
  return executionResult.getCommandRunStartTimeInNanos();
}
