private static HystrixCommandUtilization sampleCommandUtilization(HystrixCommandMetrics commandMetrics){
  return HystrixCommandUtilization.sample(commandMetrics);
}
