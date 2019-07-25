public void clear(String reason){
  logger.debug("clearing all bitsets because [{}]",reason);
  loadedFilters.invalidateAll();
}
