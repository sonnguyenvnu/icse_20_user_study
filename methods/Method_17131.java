/** 
 * Specifies that active entries are eligible for automatic refresh once a fixed duration has elapsed after the entry's creation, or the most recent replacement of its value. The semantics of refreshes are specified in  {@link LoadingCache#refresh}, and are performed by calling {@link CacheLoader#reload}. <p> Automatic refreshes are performed when the first stale request for an entry occurs. The request triggering refresh will make an asynchronous call to  {@link CacheLoader#reload} and immediatelyreturn the old value. <p> <b>Note:</b> <i>all exceptions thrown during refresh will be logged and then swallowed</i>. <p> If you can represent the duration as a  {@link java.time.Duration} (which should be preferredwhen feasible), use  {@link #refreshAfterWrite(Duration)} instead.
 * @param duration the length of time after an entry is created that it should be consideredstale, and thus eligible for refresh
 * @param unit the unit that {@code duration} is expressed in
 * @return this {@code Caffeine} instance (for chaining)
 * @throws IllegalArgumentException if {@code duration} is zero or negative
 * @throws IllegalStateException if the refresh interval was already set
 */
@NonNull public Caffeine<K,V> refreshAfterWrite(@NonNegative long duration,@NonNull TimeUnit unit){
  requireNonNull(unit);
  requireState(refreshNanos == UNSET_INT,"refresh was already set to %s ns",refreshNanos);
  requireArgument(duration > 0,"duration must be positive: %s %s",duration,unit);
  this.refreshNanos=unit.toNanos(duration);
  return this;
}
