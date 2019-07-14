/** 
 * Clears cache for the specified hystrix command.
 */
protected void flushCache(){
  if (cacheRemoveInvocationContext != null) {
    HystrixRequestCacheManager.getInstance().clearCache(cacheRemoveInvocationContext);
  }
}
