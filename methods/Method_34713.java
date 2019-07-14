private static boolean hasExecutedCommandsOnThread(HystrixThreadPoolMetrics threadPoolMetrics){
  return threadPoolMetrics.getCurrentCompletedTaskCount().intValue() > 0;
}
