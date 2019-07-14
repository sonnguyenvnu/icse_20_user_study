private static HystrixCommandMetrics initMetrics(HystrixCommandMetrics fromConstructor,HystrixCommandGroupKey groupKey,HystrixThreadPoolKey threadPoolKey,HystrixCommandKey commandKey,HystrixCommandProperties properties){
  if (fromConstructor == null) {
    return HystrixCommandMetrics.getInstance(commandKey,groupKey,threadPoolKey,properties);
  }
 else {
    return fromConstructor;
  }
}
