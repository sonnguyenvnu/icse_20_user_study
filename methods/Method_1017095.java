@Override public CacheComponent module(PrimaryComponent primary){
  return DaggerNoopCacheModule_C.builder().primaryComponent(primary).build();
}
