/** 
 * Creates and sets Hystrix thread pool properties.
 * @param properties the collapser properties
 */
public static HystrixThreadPoolProperties.Setter initializeThreadPoolProperties(List<HystrixProperty> properties) throws IllegalArgumentException {
  return initializeProperties(HystrixThreadPoolProperties.Setter(),properties,TP_PROP_MAP,"thread pool");
}
