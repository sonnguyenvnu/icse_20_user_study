public void start() throws Exception {
  try {
    int threadPoolSize=jackhammerConfiguration.getToolManagerConfiguration().getThreadPoolSize();
    int initialDelay=jackhammerConfiguration.getToolManagerConfiguration().getInitialDelay();
    int period=jackhammerConfiguration.getToolManagerConfiguration().getPeriod();
    ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(threadPoolSize);
    executor.scheduleAtFixedRate(hangedToolInstanceCheck,initialDelay,period / 2,TimeUnit.MINUTES);
  }
 catch (  Throwable th) {
    log.error("Error in ToolPooler while pooling",th);
  }
}
