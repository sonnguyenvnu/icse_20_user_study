public CacheErrorHandler errorHandler(){
  return new CacheErrorHandler(){
    @Override public void handleCacheGetError(    RuntimeException exception,    Cache cache,    Object key){
      logger.warn("handleCacheGetError in redis: {}",exception.getMessage());
    }
    @Override public void handleCachePutError(    RuntimeException exception,    Cache cache,    Object key,    Object value){
      logger.warn("handleCachePutError in redis: {}",exception.getMessage());
    }
    @Override public void handleCacheEvictError(    RuntimeException exception,    Cache cache,    Object key){
      logger.warn("handleCacheEvictError in redis: {}",exception.getMessage());
    }
    @Override public void handleCacheClearError(    RuntimeException exception,    Cache cache){
      logger.warn("handleCacheClearError in redis: {}",exception.getMessage());
    }
  }
;
}
