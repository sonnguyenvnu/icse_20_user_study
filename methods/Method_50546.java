/** 
 * Hystrix concurrency strategy hystrix concurrency strategy.
 * @return the hystrix concurrency strategy
 */
@Bean @ConditionalOnProperty(name="feign.hystrix.enabled") public HystrixConcurrencyStrategy hystrixConcurrencyStrategy(){
  return new HmilyHystrixConcurrencyStrategy();
}
