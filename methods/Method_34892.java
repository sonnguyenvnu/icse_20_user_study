public void scheduleAll(List<WebURL> urls){
  int maxPagesToFetch=config.getMaxPagesToFetch();
synchronized (mutex) {
    int newScheduledPage=0;
    for (    WebURL url : urls) {
      if ((maxPagesToFetch > 0) && ((scheduledPages + newScheduledPage) >= maxPagesToFetch)) {
        break;
      }
      try {
        workQueues.put(url);
        newScheduledPage++;
      }
 catch (      DatabaseException e) {
        logger.error("Error while putting the url in the work queue",e);
      }
    }
    if (newScheduledPage > 0) {
      scheduledPages+=newScheduledPage;
      counters.increment(Counters.ReservedCounterNames.SCHEDULED_PAGES,newScheduledPage);
    }
synchronized (waitingList) {
      waitingList.notifyAll();
    }
  }
}
