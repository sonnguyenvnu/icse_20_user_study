/** 
 * Duration of percentile rolling window in milliseconds. This is passed into  {@link HystrixRollingPercentile} inside {@link HystrixCollapserMetrics}.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> metricsRollingPercentileWindowInMilliseconds(){
  return metricsRollingPercentileWindowInMilliseconds;
}
