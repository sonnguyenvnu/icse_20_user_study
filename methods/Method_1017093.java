@Override public CacheComponent module(PrimaryComponent primary){
  return DaggerMemcachedCacheModule_C.builder().primaryComponent(primary).memcachedCacheModule(this).build();
}
