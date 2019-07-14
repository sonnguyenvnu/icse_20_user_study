@TransactionalEventListener(condition="!#event.all") @Caching(evict={@CacheEvict(value=CacheConstants.USER_AUTH_CACHE_NAME,key="#event.getUserId()"),@CacheEvict(value=CacheConstants.USER_MENU_CACHE_NAME,key="'user-menu-list:'+#event.getUserId()"),@CacheEvict(value=CacheConstants.USER_MENU_CACHE_NAME,key="'menu-tree:'+#event.getUserId()")}) public void clearUserCache(ClearUserAuthorizationCacheEvent event){
  logger.debug("clear user:{} authorization cache",event.getUserId());
}
