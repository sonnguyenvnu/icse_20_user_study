@Override public CacheComponent module(PrimaryComponent primary){
  return DaggerMemoryCacheModule_C.builder().primaryComponent(primary).build();
}
