/** 
 * Get an instance of  {@link HystrixMetricsPublisherCollapser} with the given factory {@link HystrixMetricsPublisher} implementation for each {@link HystrixCollapser} instance.
 * @param collapserKey Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCollapser} implementation
 * @param metrics Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCollapser} implementation
 * @param properties Pass-thru to  {@link HystrixMetricsPublisher#getMetricsPublisherForCollapser} implementation
 * @return {@link HystrixMetricsPublisherCollapser} instance
 */
public static HystrixMetricsPublisherCollapser createOrRetrievePublisherForCollapser(HystrixCollapserKey collapserKey,HystrixCollapserMetrics metrics,HystrixCollapserProperties properties){
  return SINGLETON.getPublisherForCollapser(collapserKey,metrics,properties);
}
