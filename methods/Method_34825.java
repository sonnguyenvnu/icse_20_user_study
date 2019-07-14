/** 
 * Get an instance of  {@link HystrixMetricsPublisherCommand} with the given factory {@link HystrixMetricsPublisher} implementation for each {@link HystrixCommand} instance.
 * @param commandKey Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCommand} implementation
 * @param commandOwner Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCommand} implementation
 * @param metrics Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCommand} implementation
 * @param circuitBreaker Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCommand} implementation
 * @param properties Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCommand} implementation
 * @return {@link HystrixMetricsPublisherCommand} instance
 */
public static HystrixMetricsPublisherCommand createOrRetrievePublisherForCommand(HystrixCommandKey commandKey,HystrixCommandGroupKey commandOwner,HystrixCommandMetrics metrics,HystrixCircuitBreaker circuitBreaker,HystrixCommandProperties properties){
  return SINGLETON.getPublisherForCommand(commandKey,commandOwner,metrics,circuitBreaker,properties);
}
