@Override public void intercept(Invocation inv){
  Method method=inv.getMethod();
  CacheEvict cacheEvict=method.getAnnotation(CacheEvict.class);
  if (cacheEvict == null) {
    inv.invoke();
    return;
  }
  Class targetClass=inv.getTarget().getClass();
  if (cacheEvict.beforeInvocation()) {
    Utils.doCacheEvict(inv.getArgs(),targetClass,method,cacheEvict);
  }
  inv.invoke();
  if (!cacheEvict.beforeInvocation()) {
    Utils.doCacheEvict(inv.getArgs(),targetClass,method,cacheEvict);
  }
}
