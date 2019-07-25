/** 
 * The last aggregation in the chain determines the cadence.
 */
@Override public long cadence(){
  return chain.stream().map(AggregationInstance::cadence).filter(c -> c >= 0).reduce((a,b) -> b).orElse(-1L);
}
