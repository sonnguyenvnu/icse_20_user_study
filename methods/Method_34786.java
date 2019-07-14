public static HystrixCommandUtilization sample(HystrixCommandMetrics commandMetrics){
  return new HystrixCommandUtilization(commandMetrics.getCurrentConcurrentExecutionCount());
}
