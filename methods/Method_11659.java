public int getTotalPageCount(){
  if (spider.getScheduler() instanceof MonitorableScheduler) {
    return ((MonitorableScheduler)spider.getScheduler()).getTotalRequestsCount(spider);
  }
  logger.warn("Get totalPageCount fail, try to use a Scheduler implement MonitorableScheduler for monitor count!");
  return -1;
}
