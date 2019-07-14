private static HystrixCircuitBreaker initCircuitBreaker(boolean enabled,HystrixCircuitBreaker fromConstructor,HystrixCommandGroupKey groupKey,HystrixCommandKey commandKey,HystrixCommandProperties properties,HystrixCommandMetrics metrics){
  if (enabled) {
    if (fromConstructor == null) {
      return HystrixCircuitBreaker.Factory.getInstance(commandKey,groupKey,properties,metrics);
    }
 else {
      return fromConstructor;
    }
  }
 else {
    return new NoOpCircuitBreaker();
  }
}
