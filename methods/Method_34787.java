public static HystrixThreadPoolUtilization sample(HystrixThreadPoolMetrics threadPoolMetrics){
  return new HystrixThreadPoolUtilization(threadPoolMetrics.getCurrentActiveCount().intValue(),threadPoolMetrics.getCurrentCorePoolSize().intValue(),threadPoolMetrics.getCurrentPoolSize().intValue(),threadPoolMetrics.getCurrentQueueSize().intValue());
}
