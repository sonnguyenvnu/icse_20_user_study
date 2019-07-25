public static boolean parse(CacheInvokeConfig cac,Method method){
  boolean hasAnnotation=false;
  CachedAnnoConfig cachedConfig=parseCached(method);
  if (cachedConfig != null) {
    cac.setCachedAnnoConfig(cachedConfig);
    hasAnnotation=true;
  }
  boolean enable=parseEnableCache(method);
  if (enable) {
    cac.setEnableCacheContext(true);
    hasAnnotation=true;
  }
  CacheInvalidateAnnoConfig invalidateAnnoConfig=parseCacheInvalidate(method);
  if (invalidateAnnoConfig != null) {
    cac.setInvalidateAnnoConfig(invalidateAnnoConfig);
    hasAnnotation=true;
  }
  CacheUpdateAnnoConfig updateAnnoConfig=parseCacheUpdate(method);
  if (updateAnnoConfig != null) {
    cac.setUpdateAnnoConfig(updateAnnoConfig);
    hasAnnotation=true;
  }
  if (cachedConfig != null && (invalidateAnnoConfig != null || updateAnnoConfig != null)) {
    throw new CacheConfigException("@Cached can't coexists with @CacheInvalidate or @CacheUpdate: " + method);
  }
  return hasAnnotation;
}
