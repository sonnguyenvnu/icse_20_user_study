/** 
 * Specifies that active entries are eligible for automatic refresh once a fixed duration has elapsed after the entry's creation, or the most recent replacement of its value. The semantics of refreshes are specified in  {@link LoadingCache#refresh}, and are performed by calling  {@link CacheLoader#reload}. <p> Automatic refreshes are performed when the first stale request for an entry occurs. The request triggering refresh will make an asynchronous call to  {@link CacheLoader#reload} and immediatelyreturn the old value. <p> <b>Note:</b> <i>all exceptions thrown during refresh will be logged and then swallowed</i>.
 * @param duration the length of time after an entry is created that it should be consideredstale, and thus eligible for refresh
 * @return this {@code Caffeine} instance (for chaining)
 * @throws IllegalArgumentException if {@code duration} is negative
 * @throws IllegalStateException if the refresh interval was already set
 * @throws ArithmeticException for durations greater than +/- approximately 292 years
 */
@NonNull public Caffeine<K,V> refreshAfterWrite(@NonNull Duration duration){
  return refreshAfterWrite(saturatedToNanos(duration),TimeUnit.NANOSECONDS);
}
