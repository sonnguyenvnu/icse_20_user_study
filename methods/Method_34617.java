/** 
 * Number of emissions of the execution of a command.  Only interesting in the streaming case.
 * @return number of <code>OnNext</code> emissions by a streaming command
 */
@Override public int getNumberEmissions(){
  return getCommandResult().getEventCounts().getCount(HystrixEventType.EMIT);
}
