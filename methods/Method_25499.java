/** 
 * Returns a statistics collector that will ignore any statistics added to it, always returning an empty result for  {@link #counters}.
 */
static StatisticsCollector createNoOpCollector(){
  return new StatisticsCollector(){
    @Override public void incrementCounter(    String key,    int count){
    }
    @Override public ImmutableMultiset<String> counters(){
      return ImmutableMultiset.of();
    }
  }
;
}
