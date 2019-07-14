/** 
 * Get an instance of  {@link HystrixMetricsPublisherThreadPool} with the given factory {@link HystrixMetricsPublisher} implementation for each {@link HystrixThreadPool} instance.
 * @param threadPoolKey Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForThreadPool} implementation
 * @param metrics Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForThreadPool} implementation
 * @param properties Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForThreadPool} implementation
 * @return {@link HystrixMetricsPublisherThreadPool} instance
 */
public static HystrixMetricsPublisherThreadPool createOrRetrievePublisherForThreadPool(HystrixThreadPoolKey threadPoolKey,HystrixThreadPoolMetrics metrics,HystrixThreadPoolProperties properties){
  return SINGLETON.getPublisherForThreadPool(threadPoolKey,metrics,properties);
}
