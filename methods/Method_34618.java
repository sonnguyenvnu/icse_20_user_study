/** 
 * Number of emissions of the execution of a fallback.  Only interesting in the streaming case.
 * @return number of <code>OnNext</code> emissions by a streaming fallback
 */
@Override public int getNumberFallbackEmissions(){
  return getCommandResult().getEventCounts().getCount(HystrixEventType.FALLBACK_EMIT);
}
