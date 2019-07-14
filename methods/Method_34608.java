protected boolean isRequestCachingEnabled(){
  return properties.requestCacheEnabled().get() && getCacheKey() != null;
}
