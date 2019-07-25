@Override public void intercept(Invocation inv){
  Method method=inv.getMethod();
  CachesEvict cachesEvict=method.getAnnotation(CachesEvict.class);
  if (cachesEvict == null) {
    inv.invoke();
    return;
  }
  CacheEvict[] evicts=cachesEvict.value();
  List<CacheEvict> beforeInvocations=null;
  List<CacheEvict> afterInvocations=null;
  for (  CacheEvict evict : evicts) {
    if (evict.beforeInvocation()) {
      if (beforeInvocations == null)       beforeInvocations=new ArrayList<>();
      beforeInvocations.add(evict);
    }
 else {
      if (afterInvocations == null)       afterInvocations=new ArrayList<>();
      afterInvocations.add(evict);
    }
  }
  Class targetClass=inv.getTarget().getClass();
  try {
    doCachesEvict(inv.getArgs(),targetClass,method,beforeInvocations);
    inv.invoke();
  }
  finally {
    doCachesEvict(inv.getArgs(),targetClass,method,afterInvocations);
  }
}
