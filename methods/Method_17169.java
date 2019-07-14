@Override @SuppressWarnings("FutureReturnValueIgnored") default void refresh(K key){
  requireNonNull(key);
  long[] writeTime=new long[1];
  long startTime=cache().statsTicker().read();
  V oldValue=cache().getIfPresentQuietly(key,writeTime);
  CompletableFuture<V> refreshFuture=(oldValue == null) ? cacheLoader().asyncLoad(key,cache().executor()) : cacheLoader().asyncReload(key,oldValue,cache().executor());
  refreshFuture.whenComplete((newValue,error) -> {
    long loadTime=cache().statsTicker().read() - startTime;
    if (error != null) {
      logger.log(Level.WARNING,"Exception thrown during refresh",error);
      cache().statsCounter().recordLoadFailure(loadTime);
      return;
    }
    boolean[] discard=new boolean[1];
    cache().compute(key,(k,currentValue) -> {
      if (currentValue == null) {
        return newValue;
      }
 else       if (currentValue == oldValue) {
        long expectedWriteTime=writeTime[0];
        if (cache().hasWriteTime()) {
          cache().getIfPresentQuietly(key,writeTime);
        }
        if (writeTime[0] == expectedWriteTime) {
          return newValue;
        }
      }
      discard[0]=true;
      return currentValue;
    }
,false,false,true);
    if (discard[0] && cache().hasRemovalListener()) {
      cache().notifyRemoval(key,newValue,RemovalCause.REPLACED);
    }
    if (newValue == null) {
      cache().statsCounter().recordLoadFailure(loadTime);
    }
 else {
      cache().statsCounter().recordLoadSuccess(loadTime);
    }
  }
);
}
