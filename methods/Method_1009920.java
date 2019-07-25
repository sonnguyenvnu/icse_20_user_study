@NonNull @Override public Cache build(CacheType type){
  return new LruCache(type.calculateCacheSize(Component.getApplication()));
}
