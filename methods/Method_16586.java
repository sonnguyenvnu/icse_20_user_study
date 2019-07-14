@TransactionalEventListener @CacheEvict(allEntries=true) public void handleClearCache(ClearPersonCacheEvent event){
  logger.debug("clear all person cache");
}
