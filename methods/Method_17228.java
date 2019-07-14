/** 
 * Returns an accumulator that does not record any cache events.
 * @return an accumulator that does not record metrics
 */
static @NonNull StatsCounter disabledStatsCounter(){
  return DisabledStatsCounter.INSTANCE;
}
