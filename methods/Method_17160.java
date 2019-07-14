@SuppressWarnings({"FutureReturnValueIgnored","NullAway"}) default CompletableFuture<V> get(K key,BiFunction<? super K,Executor,CompletableFuture<V>> mappingFunction,boolean recordStats){
  long startTime=cache().statsTicker().read();
  @SuppressWarnings({"unchecked","rawtypes"}) CompletableFuture<V>[] result=new CompletableFuture[1];
  CompletableFuture<V> future=cache().computeIfAbsent(key,k -> {
    result[0]=mappingFunction.apply(key,cache().executor());
    return requireNonNull(result[0]);
  }
,recordStats,false);
  if (result[0] != null) {
    handleCompletion(key,result[0],startTime,false);
  }
  return future;
}
