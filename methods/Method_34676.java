/** 
 * Number of buckets the rolling statistical window is broken into. This is passed into  {@link HystrixRollingNumber} inside {@link HystrixCollapserMetrics}.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> metricsRollingStatisticalWindowBuckets(){
  return metricsRollingStatisticalWindowBuckets;
}
