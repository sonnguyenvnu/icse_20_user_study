public CacheKey buildCacheKey(Class target,Method method){
  return new CacheKey(ClassUtils.getUserClass(target),method);
}
