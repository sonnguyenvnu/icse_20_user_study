/** 
 * Number of buckets the rolling percentile window is broken into. This is passed into  {@link HystrixRollingPercentile} inside {@link HystrixCommandMetrics}.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> metricsRollingPercentileWindowBuckets(){
  return metricsRollingPercentileWindowBuckets;
}
