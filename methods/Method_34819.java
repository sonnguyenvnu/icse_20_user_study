/** 
 * Register a  {@link HystrixMetricsPublisher} implementation as a global override of any injected or default implementations.
 * @param impl {@link HystrixMetricsPublisher} implementation
 * @throws IllegalStateException if called more than once or after the default was initialized (if usage occurs before trying to register)
 */
public void registerMetricsPublisher(HystrixMetricsPublisher impl){
  if (!metricsPublisher.compareAndSet(null,impl)) {
    throw new IllegalStateException("Another strategy was already registered.");
  }
}
