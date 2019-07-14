/** 
 * The execution time of this command instance in milliseconds, or -1 if not executed.
 * @return int
 */
public int getExecutionTimeInMilliseconds(){
  return getCommandResult().getExecutionLatency();
}
