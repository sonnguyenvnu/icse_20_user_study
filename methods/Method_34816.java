/** 
 * Reset all of the HystrixPlugins to null.  You may invoke this directly, or it also gets invoked via <code>Hystrix.reset()</code>
 */
public static void reset(){
  getInstance().notifier.set(null);
  getInstance().concurrencyStrategy.set(null);
  getInstance().metricsPublisher.set(null);
  getInstance().propertiesFactory.set(null);
  getInstance().commandExecutionHook.set(null);
  HystrixMetricsPublisherFactory.reset();
}
