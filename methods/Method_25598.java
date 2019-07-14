/** 
 * Returns a copy of all of the counters previously added to this VisitorState with  {@link #incrementCounter}.
 */
public ImmutableMultiset<String> counters(){
  return statisticsCollector.counters();
}
