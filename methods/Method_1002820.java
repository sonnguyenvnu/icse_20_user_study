@Override default CompletableFuture<AggregatedHttpFile> aggregate(Executor fileReadExecutor){
  return CompletableFuture.completedFuture(this);
}
