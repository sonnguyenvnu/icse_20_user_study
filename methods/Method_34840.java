/** 
 * Construct an implementation of  {@link HystrixCollapserProperties} for {@link HystrixCollapser} instances with {@link HystrixCollapserKey}. <p> <b>Default Implementation</b> <p> Constructs instance of  {@link HystrixPropertiesCollapserDefault}.
 * @param collapserKey {@link HystrixCollapserKey} representing the name or type of {@link HystrixCollapser}
 * @param builder {@link com.netflix.hystrix.HystrixCollapserProperties.Setter} with default overrides as injected to the {@link HystrixCollapser} implementation.<p> The builder will return NULL for each value if no override was provided.
 * @return Implementation of {@link HystrixCollapserProperties}
 */
public HystrixCollapserProperties getCollapserProperties(HystrixCollapserKey collapserKey,HystrixCollapserProperties.Setter builder){
  return new HystrixPropertiesCollapserDefault(collapserKey,builder);
}
