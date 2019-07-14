public void setProcessed(WebURL webURL){
  counters.increment(Counters.ReservedCounterNames.PROCESSED_PAGES);
  if (inProcessPages != null) {
    if (!inProcessPages.removeURL(webURL)) {
      logger.warn("Could not remove: {} from list of processed pages.",webURL.getURL());
    }
  }
}
