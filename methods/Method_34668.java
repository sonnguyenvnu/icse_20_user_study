/** 
 * All registered instances of  {@link HystrixCollapserMetrics}
 * @return {@code Collection<HystrixCollapserMetrics>}
 */
public static Collection<HystrixCollapserMetrics> getInstances(){
  return Collections.unmodifiableCollection(metrics.values());
}
