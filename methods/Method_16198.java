@TransactionalEventListener(condition="#event.all") @Caching(evict={@CacheEvict(cacheNames=USER_MENU_CACHE_NAME,allEntries=true),@CacheEvict(cacheNames=USER_AUTH_CACHE_NAME,allEntries=true)}) public void clearAllUserCache(ClearUserAuthorizationCacheEvent event){
  logger.debug("clear all user authorization cache");
}
