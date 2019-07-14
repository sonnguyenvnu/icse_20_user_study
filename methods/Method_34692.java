/** 
 * Duration of statistical rolling window in milliseconds. This is passed into  {@link HystrixRollingNumber} inside {@link HystrixCommandMetrics}.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> metricsRollingStatisticalWindowInMilliseconds(){
  return metricsRollingStatisticalWindowInMilliseconds;
}
