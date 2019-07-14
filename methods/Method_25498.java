/** 
 * Returns a new statistics collector that will successfully count keys added to it. 
 */
static StatisticsCollector createCollector(){
  return new StatisticsCollector(){
    @Override public void incrementCounter(    String key,    int count){
      strings.add(key,count);
    }
    @Override public ImmutableMultiset<String> counters(){
      return ImmutableMultiset.copyOf(strings);
    }
  }
;
}
