static boolean hasLoadAll(CacheLoader<?,?> cacheLoader){
  return hasMethod(cacheLoader,"loadAll",Iterable.class);
}
