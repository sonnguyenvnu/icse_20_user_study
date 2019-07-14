/** 
 * Override to split (shard) a batch of requests into multiple batches that will each call <code>createCommand</code> separately. <p> The purpose of this is to allow collapsing to work for services that have sharded backends and batch executions that need to be shard-aware. <p> For example, a batch of 100 requests could be split into 4 different batches sharded on name (ie. a-g, h-n, o-t, u-z) that each result in a separate  {@link HystrixCommand} being created andexecuted for them. <p> By default this method does nothing to the Collection and is a pass-thru.
 * @param requests {@code Collection<CollapsedRequest<ResponseType, RequestArgumentType>>} containing {@link CollapsedRequest} objects containing the arguments of each request collapsed in this batch.
 * @return Collection of {@code Collection<CollapsedRequest<ResponseType, RequestArgumentType>>} objects sharded according to business rules.<p>The CollapsedRequest instances should not be modified or wrapped as the CollapsedRequest instance object contains state information needed to complete the execution.
 */
protected Collection<Collection<CollapsedRequest<ResponseType,RequestArgumentType>>> shardRequests(Collection<CollapsedRequest<ResponseType,RequestArgumentType>> requests){
  return Collections.singletonList(requests);
}
