@Override public boolean match(Class target,Method method){
  Strategy strategy=createStrategyIfMatch(target,method);
  if (null != strategy) {
    if (log.isDebugEnabled()) {
      log.debug("create table switcher strategy:{} for method:{}",strategy,method);
    }
    CacheKey cacheKey=new CacheKey(target,method);
    cache.put(cacheKey,strategy);
    return true;
  }
  return false;
}
