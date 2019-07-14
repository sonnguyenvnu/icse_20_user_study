/** 
 * Returns the elapsed durations of each timer. 
 */
public Map<String,Duration> timings(){
  return timers.entrySet().stream().collect(toImmutableMap(e -> e.getKey(),e -> e.getValue().elapsed()));
}
