/** 
 * Returns an accumulator that suppresses and logs any exception thrown by the delegate {@code statsCounter}.
 * @param statsCounter the accumulator to delegate to
 * @return an accumulator that suppresses and logs any exception thrown by the delegate
 */
static @NonNull StatsCounter guardedStatsCounter(@NonNull StatsCounter statsCounter){
  return new GuardedStatsCounter(statsCounter);
}
