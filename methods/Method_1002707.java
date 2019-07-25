/** 
 * Aggregates this request. The returned  {@link CompletableFuture} will be notified when the content andthe trailers of the request is received fully.
 */
default CompletableFuture<AggregatedHttpRequest> aggregate(EventExecutor executor){
  requireNonNull(executor,"executor");
  final CompletableFuture<AggregatedHttpRequest> future=new CompletableFuture<>();
  final HttpRequestAggregator aggregator=new HttpRequestAggregator(this,future,null);
  subscribe(aggregator,executor);
  return future;
}
