public void schedule(WebURL url){
  int maxPagesToFetch=config.getMaxPagesToFetch();
synchronized (mutex) {
    try {
      if (maxPagesToFetch < 0 || scheduledPages < maxPagesToFetch) {
        workQueues.put(url);
        scheduledPages++;
        counters.increment(Counters.ReservedCounterNames.SCHEDULED_PAGES);
      }
    }
 catch (    DatabaseException e) {
      logger.error("Error while putting the url in the work queue",e);
    }
  }
}
