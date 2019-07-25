public void start(){
  try {
    toolInstanceDAO.deleteAll();
    scanDAO.addProgressingScansToQueue();
    int threadPoolSize=jackhammerConfiguration.getScanMangerConfiguration().getThreadPoolSize();
    int initialDelay=jackhammerConfiguration.getScanMangerConfiguration().getInitialDelay();
    int period=jackhammerConfiguration.getScanMangerConfiguration().getPeriod();
    ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(threadPoolSize);
    executor.scheduleAtFixedRate(scanTask,initialDelay,period,TimeUnit.SECONDS);
  }
 catch (  Exception e) {
    log.error("Error while running scanTask..",e);
  }
catch (  Throwable th) {
    log.error("Error while running scanTask..",th);
  }
}
