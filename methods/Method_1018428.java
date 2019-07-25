@HystrixCommand(groupKey="hystrixThreadTestGroupKey",commandKey="hystrixThreadTestCommandKey",fallbackMethod="fallbackMethodThread",commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="10000"),@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="90"),@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="60000"),@HystrixProperty(name="execution.isolation.strategy",value="THREAD"),@HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests",value="100"),@HystrixProperty(name="metrics.rollingPercentile.timeInMilliseconds",value="600000")},threadPoolProperties={@HystrixProperty(name="coreSize",value="10"),@HystrixProperty(name="maxQueueSize",value="100"),@HystrixProperty(name="keepAliveTimeMinutes",value="2"),@HystrixProperty(name="metrics.rollingStats.numBuckets",value="12"),@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="1440")}) @Override public Result thread(String arg){
  redisTemplate.opsForValue().get("thread");
  Person person=new Person();
  person.setAge(18);
  person.setId(2L);
  person.setName("??thread");
  person.setAddress("??thread");
  logger.info(JSON.toJSONString(person));
  return Result.success(person);
}
