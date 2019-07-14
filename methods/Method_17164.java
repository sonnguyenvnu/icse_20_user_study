/** 
 * Returns whether the supplied cache loader has bulk load functionality. 
 */
private static boolean canBulkLoad(AsyncCacheLoader<?,?> loader){
  try {
    Class<?> defaultLoaderClass=AsyncCacheLoader.class;
    if (loader instanceof CacheLoader<?,?>) {
      defaultLoaderClass=CacheLoader.class;
      Method classLoadAll=loader.getClass().getMethod("loadAll",Iterable.class);
      Method defaultLoadAll=CacheLoader.class.getMethod("loadAll",Iterable.class);
      if (!classLoadAll.equals(defaultLoadAll)) {
        return true;
      }
    }
    Method classAsyncLoadAll=loader.getClass().getMethod("asyncLoadAll",Iterable.class,Executor.class);
    Method defaultAsyncLoadAll=defaultLoaderClass.getMethod("asyncLoadAll",Iterable.class,Executor.class);
    return !classAsyncLoadAll.equals(defaultAsyncLoadAll);
  }
 catch (  NoSuchMethodException|SecurityException e) {
    logger.log(Level.WARNING,"Cannot determine if CacheLoader can bulk load",e);
    return false;
  }
}
