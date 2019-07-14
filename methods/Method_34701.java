/** 
 * Get the rolling count for the given  {@link HystrixRollingNumberEvent}. <p> The rolling window is defined by  {@link HystrixCommandProperties#metricsRollingStatisticalWindowInMilliseconds()}.
 * @param event {@link HystrixRollingNumberEvent} of the event to retrieve a sum for
 * @return long rolling count
 */
public long getRollingCount(HystrixRollingNumberEvent event){
  return counter.getRollingSum(event);
}
