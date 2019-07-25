@HystrixCommand(groupKey="hystrixSemaphoreTestGroupKey",commandKey="hystrixSemaphoreTestCommandKey",fallbackMethod="fallbackMethodSemaphore",commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="10000"),@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="80"),@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),@HystrixProperty(name="execution.isolation.strategy",value="SEMAPHORE"),@HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests",value="10"),@HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests",value="100")}) @Override public Result semaphore(String arg){
  redisTemplate.opsForValue().get("semaphore");
  Person person=new Person();
  person.setAge(18);
  person.setId(2L);
  person.setName("??semaphore");
  person.setAddress("??semaphore");
  logger.info(JSON.toJSONString(person));
  return Result.success(person);
}
