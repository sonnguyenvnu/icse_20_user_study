public void start() throws Exception {
  int threadPoolSize=jackhammerConfiguration.getScanMangerConfiguration().getThreadPoolSize();
  int initialDelay=jackhammerConfiguration.getScanMangerConfiguration().getInitialDelay();
  ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(threadPoolSize);
  executor.scheduleAtFixedRate(wpScanSchedulerPicker,initialDelay,1,TimeUnit.DAYS);
}
