public int getLeftPageCount(){
  if (spider.getScheduler() instanceof MonitorableScheduler) {
    return ((MonitorableScheduler)spider.getScheduler()).getLeftRequestsCount(spider);
  }
  logger.warn("Get leftPageCount fail, try to use a Scheduler implement MonitorableScheduler for monitor count!");
  return -1;
}
