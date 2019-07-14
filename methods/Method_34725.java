/** 
 * Duration of statistical rolling window in milliseconds. This is passed into  {@link HystrixRollingNumber} inside each {@link HystrixThreadPoolMetrics} instance.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> metricsRollingStatisticalWindowInMilliseconds(){
  return threadPoolRollingNumberStatisticalWindowInMilliseconds;
}
