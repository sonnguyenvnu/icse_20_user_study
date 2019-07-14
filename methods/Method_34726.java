/** 
 * Number of buckets the rolling statistical window is broken into. This is passed into  {@link HystrixRollingNumber} inside each {@link HystrixThreadPoolMetrics} instance.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> metricsRollingStatisticalWindowBuckets(){
  return threadPoolRollingNumberStatisticalWindowBuckets;
}
