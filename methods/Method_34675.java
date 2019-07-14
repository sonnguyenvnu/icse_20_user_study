/** 
 * Whether request caching is enabled for  {@link HystrixCollapser#execute} and {@link HystrixCollapser#queue} invocations.Deprecated as of 1.4.0-RC7 in favor of requestCacheEnabled() (to match  {@link HystrixCommandProperties#requestCacheEnabled()}
 * @return {@code HystrixProperty<Boolean>}
 */
@Deprecated public HystrixProperty<Boolean> requestCachingEnabled(){
  return requestCacheEnabled;
}
