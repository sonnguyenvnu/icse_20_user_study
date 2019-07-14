@Override @SuppressWarnings("FutureReturnValueIgnored") default void put(K key,CompletableFuture<V> valueFuture){
  if (valueFuture.isCompletedExceptionally() || (valueFuture.isDone() && (valueFuture.join() == null))) {
    cache().statsCounter().recordLoadFailure(0L);
    cache().remove(key);
    return;
  }
  long startTime=cache().statsTicker().read();
  cache().put(key,valueFuture);
  handleCompletion(key,valueFuture,startTime,false);
}
