@Override public CompletableFuture<AggregatedHttpRequest> aggregate(){
  return delegate.aggregate().thenApply(this::replaceHeaders);
}
