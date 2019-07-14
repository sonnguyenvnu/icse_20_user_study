/** 
 * Creates batch command.
 */
@Override protected HystrixCommand<List<Object>> createCommand(Collection<CollapsedRequest<Object,Object>> collapsedRequests){
  return new BatchHystrixCommand(HystrixCommandBuilderFactory.getInstance().create(metaHolder,collapsedRequests));
}
